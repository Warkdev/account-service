package eu.getmangos.serializers;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.kafka.common.serialization.Serializer;

import eu.getmangos.dto.AccountEventDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountEventSerializer implements Serializer<AccountEventDTO> {
    private static final Jsonb jsonb = JsonbBuilder.create();

    @Override
    public byte[] serialize(String topic, AccountEventDTO data) {
        log.debug("Serializing data");
        return jsonb.toJson(data).getBytes();
    }

}
