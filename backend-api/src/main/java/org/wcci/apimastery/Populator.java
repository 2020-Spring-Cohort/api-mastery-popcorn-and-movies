package org.wcci.apimastery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator  implements CommandLineRunner  {
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ActorRepository actorRepository;

   @Override
   public void run(String... args) throws Exception {
       Genre genre1 = new Genre("Comedy");
       genreRepository.save(genre1);

       Actor bradleyCooper = new Actor ("Bradley Cooper");
       actorRepository.save(bradleyCooper);
       Actor kenJeong = new Actor ("Ken Jeong");
       actorRepository.save(kenJeong);
       Actor jonahHill = new Actor ("Jonah Hill");
       actorRepository.save(jonahHill);
       Actor michaelCera = new Actor ("Michael Cera");
       actorRepository.save(michaelCera);

       Movie comedyMovie1 = new Movie("The Hangover", genre1, bradleyCooper, kenJeong);
       movieRepository.save(comedyMovie1);
       Movie comedyMovie2 = new Movie("Superbad", genre1, jonahHill, michaelCera);
       movieRepository.save(comedyMovie2);


       Genre genre2 = new Genre("Action");
       genreRepository.save(genre2);

       Actor anthonyMackie = new Actor ("Anthony Mackie");
       actorRepository.save(anthonyMackie);
       Actor samuelJackson = new Actor ("Samuel Jackson");
       actorRepository.save(samuelJackson);
       Actor keanuReeves = new Actor ("Keanu Reeves");
       actorRepository.save(keanuReeves);
       Actor laurenceFishburne = new Actor ("Laurence Fishburne");
       actorRepository.save(laurenceFishburne);

       Movie actionMovie1 = new Movie("The Banker", genre2, samuelJackson, anthonyMackie);
       movieRepository.save(actionMovie1);
       Movie actionMovie2 = new Movie("The Matrix", genre2, keanuReeves, laurenceFishburne);
       movieRepository.save(actionMovie2);

       Genre genre3 = new Genre("Drama");
       genreRepository.save(genre3);

       Actor leonardoDiCaprio = new Actor ("Leonardo DiCaprio");
       actorRepository.save(leonardoDiCaprio);
       Actor kateWinslet = new Actor ("Kate Winslet");
       actorRepository.save(kateWinslet);
       Actor bradPitt = new Actor ("Brad Pitt");
       actorRepository.save(bradPitt);
       Actor edwardNorton = new Actor ("Edward Norton");
       actorRepository.save(edwardNorton);

       Movie dramaMovie1 = new Movie("Titanic", genre3, leonardoDiCaprio, kateWinslet);
       movieRepository.save(dramaMovie1);
       Movie dramaMovie2 = new Movie("Fight Club", genre3, bradPitt, edwardNorton);
       movieRepository.save(dramaMovie2);



   }
}
