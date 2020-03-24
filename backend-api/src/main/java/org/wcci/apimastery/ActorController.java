package org.wcci.apimastery;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ActorController {
    private  ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;

    }
    @RequestMapping("/actors")
    public Collection<Actor> retrievedActors() {
        return (Collection<Actor>) actorRepository.findAll();
    }


}
