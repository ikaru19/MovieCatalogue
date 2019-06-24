package com.syafrizal.submission2;

import com.syafrizal.submission2.Models.Movie;

import java.util.ArrayList;

public class MoviesData {
    public static Movie[] data = new Movie[]{
            new Movie(1,R.drawable.poster_a_start_is_born,"A Star Is Born","Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.","5-10-2018"),
            new Movie(1,R.drawable.poster_alita,"Alita : Battle Angel","When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.","14-2-2019"),
            new Movie(1,R.drawable.poster_aquaman,"Aquaman","Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.","21-12-2018"),
            new Movie(0,R.drawable.poster_arrow,"Arrow","Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.","02-02-2012"),
            new Movie(1,R.drawable.poster_bohemian,"Bohemian Rhapsody","Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.","30-10-2018"),
            new Movie(1,R.drawable.poster_cold_persuit,"Cold Pursuit","The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.","8-2-2019"),
            new Movie(1,R.drawable.poster_creed,"Creed","The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.","25-11-2015"),
            new Movie(1,R.drawable.poster_crimes,"Fantastic Beasts : The Crimes of Grindelwald","Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.","16-11-2018"),
            new Movie(0,R.drawable.poster_doom_patrol,"Doom Patrol","The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief","15-2-2019"),
            new Movie(0,R.drawable.poster_dragon_ball,"Dragon Ball","Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his…","26-2-1986"),
            new Movie(0,R.drawable.poster_fairytail,"Fairy Tail","Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary","12-10-2009"),
            new Movie(0,R.drawable.poster_family_guy,"Family Guy","Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but…","31-01-1999"),
            new Movie(0,R.drawable.poster_flash,"The Flash","After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move…","7-10-2014"),
            new Movie(1,R.drawable.poster_glass,"Glass","In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence…","16-01-2019"),
            new Movie(0,R.drawable.poster_god,"Game of Thrones","Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected…","17-04-2011"),
            new Movie(0,R.drawable.poster_gotham,"Gotham","Before there was Batman, there was GOTHAM. Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known…","22-09-2014"),
            new Movie(0,R.drawable.poster_grey_anatomy,"Grey's Anatomy","Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.","27-03-2005"),
            new Movie(0,R.drawable.poster_hanna,"Hanna","This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the…","28-03-2019"),
            new Movie(1,R.drawable.poster_how_to_train,"How to Train Your Dragon","As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes…","10-03-2010"),
            new Movie(1,R.drawable.poster_infinity_war,"Avengers : Infinity War","As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy…","25-04-2018"),
            new Movie(0,R.drawable.poster_iron_fist,"Marvel's iron Fist","Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.","17-03-2017"),


    };

    public static ArrayList<Movie> getMoviesList(){
        ArrayList<Movie> list = new ArrayList<>();
        for (Movie aData : data){
            if (aData.getCategory() == 1){
                list.add(aData);
            }
        }
        return list;
    }

    public static ArrayList<Movie> getShowsList(){
        ArrayList<Movie> list = new ArrayList<>();
        for (Movie aData : data){
            if (aData.getCategory() == 0){
                list.add(aData);
            }
        }
        return list;
    }
}
