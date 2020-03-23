package org.wcci.apimastery;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActorControllerTest {

    private ActorRepository actorRepository;
    private Actor testActor;
    

    @Test
    public void retrievedActorShouldReturnActorFromMockRepo(){
        actorRepository = mock(ActorRepository.class);
        Genre testGenre = new Genre("Comedy");
        Movie testMovie = new Movie("Hangover", testGenre);
        ActorController underTest = new ActorController(actorRepository);
        Actor testActor = new Actor("Bradley");
        when(actorRepository.findAll()).thenReturn(Collections.singletonList(testActor));
        Collection<Actor> result = underTest.retrievedActors();
        verify(actorRepository).findAll();
    }

    @Test
    public void retrievedActorShouldReturnAListOfActorContainingMockActors() {
        actorRepository = mock(ActorRepository.class);
        Genre testGenre = new Genre("Comedy");
        Movie testMovie = new Movie("Hangover", testGenre);
        ActorController underTest = new ActorController(actorRepository);
        Actor testActor = new Actor("Bradley");
        when(actorRepository.findAll()).thenReturn(Collections.singletonList(testActor));
        Collection<Actor> result = underTest.retrievedActors();
        verify(actorRepository).findAll();
        assertThat(result).contains(testActor);
    }
    @Test
    public void underTestIsWiredCorrectlyWithAnnotation() throws Exception {
        actorRepository = mock(ActorRepository.class);
        ActorController underTest = new ActorController(actorRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/actors")).andExpect(status().isOk());
    }
}
