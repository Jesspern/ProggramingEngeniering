package org.example.eventaggregator;

import lombok.RequiredArgsConstructor;
import org.example.eventaggregator.event.mediator.EventMediator;
import org.example.eventaggregator.event.observer.impl.LoggingObserver;
import org.example.eventaggregator.model.User;
import org.example.eventaggregator.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DemoRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final EventMediator mediator;
    private final LoggingObserver loggingObserver;

    @Override
    public void run(String... args) {
        // Регистрируем наблюдателя
        mediator.registerObserver(loggingObserver);

        // Демонстрация CRUD-операций
        User user = new User();
        user.setName("Test User");
        userRepository.save(user);  // CREATE

        user.setName("Updated User");
        userRepository.save(user);  // UPDATE

        userRepository.delete(user); // DELETE
    }
}
