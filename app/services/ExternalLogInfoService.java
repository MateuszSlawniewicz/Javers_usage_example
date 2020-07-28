package services;

import models.Quote;
import play.libs.ws.WSClient;

import javax.inject.Inject;


public class ExternalLogInfoService {
    private final static String API_LOG_URL = "https://pipedream.net/";
    private final WSClient wsClient;
    private final ModelMapper modelMapper;

    @Inject
    public ExternalLogInfoService(WSClient wsClient, ModelMapper modelMapper) {
        this.wsClient = wsClient;
        this.modelMapper = modelMapper;
    }

    public void senLogInfo(Quote quote) {
        wsClient.url(API_LOG_URL)
                .post(modelMapper.jsonToPostForLog(quote));
    }

}
