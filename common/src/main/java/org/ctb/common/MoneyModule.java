package org.ctb.common;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;

public class MoneyModule extends SimpleModule {

    class MoneyDeserializer extends StdScalarDeserializer<Money> {

        protected MoneyDeserializer() {
            super(Money.class);
        }

        @Override
        public Money deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken token = jsonParser.getCurrentToken();
            if(token == JsonToken.VALUE_STRING) {
                String value = jsonParser.getText().trim();
                if(value.isEmpty()) {
                    return null;
                }
                else return new Money(value);
            } else throw new RuntimeException("Unexpected token: " + token);
        }
    }

    class MoneySerializer extends StdScalarSerializer<Money>{


        public MoneySerializer(){
            super(Money.class);
        }

        @Override
        public void serialize(Money money, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                    jsonGenerator.writeString(money.asString());
        }
    }

    public String getModuleName(){
        return "CaterBeCommonMOdule";
    }
    public MoneyModule(){
        addDeserializer(Money.class,new MoneyDeserializer());
        addSerializer(Money.class,new MoneySerializer());
    }
}
