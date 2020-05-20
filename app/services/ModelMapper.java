package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Quote;
import models.ResultModel;

import play.libs.Json;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.util.Date;

public class ModelMapper {
    private final ObjectMapper objectMapper;

    @Inject
    public ModelMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Quote transformToQuote(JsonNode q) {
        Quote quote = new Quote();
        try {
            quote = this.objectMapper.readValue(q.toString(), Quote.class);
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
        return quote;
    }


    public String QuoteToJson(Quote q) {
        try {
            return objectMapper.writeValueAsString(q);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return "";

    }


    public ResultModel JsonToResultModel(WSResponse response) {
        return Json.fromJson(response.asJson(), ResultModel.class);
    }

    public JsonNode jsonToPostForLog(Quote quote) {
        return Json.newObject()
                .put("date", new Date().toString())
                .put("quote", quote.getContent())
                .put("keyWord", quote.getKeyWord());
    }


}
