package com.research.assistant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper; 



@Service
public class ResearchService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ResearchService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    public String processContent(ResearchRequest request) {
        String prompt = buildPrompt(request);
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", prompt)
                        })
                });

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .bodyValue(requestBody).retrieve()
                .bodyToMono(String.class).block();
        return extractTextFromResponse(response);
    }

    private String buildPrompt(ResearchRequest request) {
        StringBuilder prompt = new StringBuilder();
        switch (request.getOperation()) {
            case "summarize":
                prompt.append(
                "Analyze the following content and generate a professional work log entry summary.\n")
                .append("- Write it short and crisp in bullet points using 1,2,3..\n");
                break;
            default:
                throw new IllegalArgumentException("Unknown Operation: " + request.getOperation());

        }
        prompt.append(request.getContent());
        return prompt.toString();
    }

    private String extractTextFromResponse(String resposne){
        try{
                GeminiResponse geminiResponse = objectMapper.readValue(resposne, GeminiResponse.class);
                if(geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()){
                    GeminiResponse.Candidate firstCandidate  = geminiResponse.getCandidates().get(0);
                    if(firstCandidate.getContent() != null && 
                        firstCandidate.getContent().getParts() != null &&
                        !firstCandidate.getContent().getParts().isEmpty()){
                            return firstCandidate.getContent().getParts().get(0).getText();
                    }
                }
                return "No content found in response";
        }catch(Exception e){
            return "Error Parsing "+ e.getMessage();
        }
    }

}
