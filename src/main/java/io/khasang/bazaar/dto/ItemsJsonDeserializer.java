package io.khasang.bazaar.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public class ItemsJsonDeserializer<T> extends JsonDeserializer<List<T>> {

    @Override
    public List<T> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        InnerItems innerItems = jp.readValueAs(InnerItems.class);

        return innerItems.elements;
    }

    private class InnerItems {
        public List<T> elements;
    }
}
