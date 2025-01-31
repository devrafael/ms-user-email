package com.ms.user.producers;

import com.ms.user.dtos.EmailRecord;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routinKey;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageEmail(UserModel user) {
        var emailDto = new EmailRecord(
                user.getUserId(),
                user.getEmail(),
                "Cadastro realizado com sucesso",
                user.getName() + " seja bem vindo(a)! \nAgradecemos o seu cadastro."
        );

        rabbitTemplate.convertAndSend("", routinKey, emailDto);
    }
}
