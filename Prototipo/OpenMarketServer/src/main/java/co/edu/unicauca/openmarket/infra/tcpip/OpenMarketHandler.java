package co.edu.unicauca.openmarket.infra.tcpip;

import co.unicauca.strategyserver.infra.ServerHandler;

public class OpenMarketHandler extends ServerHandler {
    private static RequestProcessor requestProcessor;

    public OpenMarketHandler() {
        
    }

    @Override
    public String processRequest(String requestJson) {
        return requestProcessor.processRequest(requestJson);
    }

    public void setRequestProcessor(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }
    
}