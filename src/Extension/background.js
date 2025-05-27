chrome.sidePanel.setPanelBehavior({openPanelOnActionClick: true})
chrome.runtime.onInstalled.addListener(() => {
  console.log("Work Log Assistant Extension Installed.");
});
