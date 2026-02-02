package com.amigoscode.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class Activities {

    @Test
    void ExerciseOne() {
        // to do: take data -> map to person -> filter teenagers -> find if any email ends in gov.uk
        String data = """ 
    16,Fanchette,Williamson,fwilliamson0@github.com,F
    26,Aleksandr,Matts,amatts1@webs.com,M
    3,Maurie,Cordero,mcordero2@google.co.jp,M
    45,Donnajean,Crowson,dcrowson3@google.com.hk,F
    5,Ricardo,Gofton,rgofton4@nytimes.com,M
    65,Gabie,Tregenna,gtregenna5@guardian.co.uk,F
    37,Marjorie,Blumsom,mblumsom6@joomla.org,F
    18,Lester,Huyghe,lhuyghe7@jugem.jp,M
    39,Merrily,Stangoe,mstangoe8@tiny.cc,F
    10,Reider,Karel,rkarel9@github.io,M
    11,Dory,Jolliff,djolliffa@wufoo.com,F
    32,Homerus,Averay,haverayb@skyrock.com,M
    13,Alyda,Muglestone,amuglestonec@is.gd,F
    14,Pinchas,Louca,ploucad@google.es,M
    11,Cherin,Eltringham,celtringhame@parallels.com,F
    2,Mufi,Rothert,mrothertf@dropbox.com,F
    17,Jordana,Everex,jeverexg@ucla.edu,F
    18,Belle,Rother,brotherh@gov.uk,F
    19,Clevie,Sifflett,csiffletti@furl.net,M
    20,Gretchen,Abell,gabellj@1688.com,F
""";
        List<Person> fromUk = data.lines().map(e ->  e.split(",")).map(p ->
            new Person(Integer.parseInt(p[0].trim()), p[1].trim(), p[2].trim(), p[3].trim(), Gender.valueOf(p[4].trim()))).
                filter(l ->l.age() >= 13 && l.age() <= 19).
                filter(m -> m.email.endsWith("gov.uk")).toList();

        fromUk.forEach(System.out::println);
    }

    record Person(int age, String firstName, String lastName, String email, Gender gender) {}
}

enum Gender {
    M,
    F
}