package com.example.project_02.GymJourney;

public class Classified {

    private String[] starwars = {"Hello there", ".. General Kenobi"};


    private String[] shine ={ "Whoa,.... Heaven let your shine down"};

    private String[] ug = {"3...2...1.. GO!"};


    private String[] patty = {"No.. this is patrick!"};

    private String[] java = {"man i love java :)"};

    private class Memory {


        public String[] $004f2bca(int r) {
            String str;
            switch(r)
            {
                case 1:
                    return starwars;
                case 2:
                    return shine;
                case 3:
                    return ug;
                case 4:
                    return patty;
                case 5:
                    return java;

            }
            return starwars;
        }

    }
    
    public String[] HackTheWorld(int $) {
        Memory m = new Memory();
        return m.$004f2bca($);
    }


}
