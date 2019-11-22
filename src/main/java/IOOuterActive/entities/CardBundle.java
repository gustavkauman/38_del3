package IOOuterActive.entities;

import java.util.Random;

public class CardBundle {
    private int currentCardPos = 0;
    private Card[] cards = new Card[20];

    public void createCards(){
        cards[0] = new Card("GiveToCar", "Giv dette kort til BILEN, og tag et chancekort mere. \n BIL: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. " +
                "\n Hvis der ikke er nogen ledige felter, skal du købe et fra en anden spiller!"); //TODO: Prob ændre bil, skib osv. til spiller 1, spiller 2 ...

        cards[1] = new Card("GoToStart", "Ryk frem til START. Modtag $2.");
        cards[2] = new Card("Move5", "Ryk op til 5 felter frem.");
        cards[3] = new Card("OrangeFree", "Ryk frem til et orange felt. \n Hvis det er ledigt, får du det gratis! Ellers skal du betale leje til ejeren.");
        cards[4] = new Card("MoveOrCard", "Ryk 1 felt frem, eller tag et chancekort mere.");

        cards[5] = new Card("GiveToShip", "Giv dette kort til SKIBET, og tag et chancekort mere. \n SKIB: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. " +
                "\n Hvis der ikke er nogen ledige felter, skal du købe et fra en anden spiller!");

        cards[6] = new Card("Overeating", "Du har spist for meget slik. Betal $2 til banken.");
        cards[7] = new Card("OrangeOrGreenFree", "Ryk frem til et orange eller grønt felt. \n Hvis det er ledigt, får du det gratis! Ellers skal du betale leje til ejeren.");
        cards[8] = new Card("LightblueFree", "Ryk frem til et lyseblåt felt. \n Hvis det er ledigt, får du det gratis! Ellers skal du betale leje til ejeren.");
        cards[9] = new Card("GetOutOfJail", "Du løslades uden omkostninger. Behold dette kort, indtil du får brug for det.");
        cards[10] = new Card("MoveToStrand", "Ryk frem til Strandpromenaden.");

        cards[11] = new Card("GiveToCat", "Giv dette kort til KATTEN, og tag et chancekort mere. \n KAT: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. " +
                "\n Hvis der ikke er nogen ledige felter, skal du købe et fra en anden spiller!");

        cards[12] = new Card("GiveToDog", "Giv dette kort til HUNDEN, og tag et chancekort mere. \n HUND: På din næste tur skal du drøne frem til et hvilket som helst ledigt felt og købe det. " +
                "\n Hvis der ikke er nogen ledige felter, skal du købe et fra en anden spiller!");

        cards[13] = new Card("Birthday", "Det er din fødselsdag! Alle giver dig $1. Tillykke med fødselsdagen!");
        cards[14] = new Card("PinkOrDarkblueFree", "Ryk frem til et pink eller mørkeblåt felt. \n Hvis det er ledigt, får du det gratis! Ellers skal du betale leje til ejeren.");
        cards[15] = new Card("Homework", "Du har lavet alle dine lektier! Modtag $2 fra banken.");
        cards[16] = new Card("RedFree", "Ryk frem til et rødt felt. \n Hvis det er ledigt, får du det gratis! Ellers skal du betale leje til ejeren.");
        cards[17] = new Card("Skatepark", "Ryk frem til Skaterparken for at lave det perfekte grind! \n Hvis ingen ejer den, får du den gratis! Ellers skal du betale leje til ejeren.");
        cards[18] = new Card("LightblueOrRedFree", "Ryk frem til et lyseblåt eller rødt felt. \n Hvis det er ledigt, får du det gratis! Ellers skal du betale leje til ejeren.");
        cards[19] = new Card("BrownOrYellowFree", "Ryk frem til et brunt eller gult felt. \n Hvis det er ledigt, får du det gratis! Ellers skal du betale leje til ejeren.");

        shuffleCards();
    }

    private void shuffleCards() {
        Random rand = new Random();

        for(int i = 0; i < cards.length; i++){
            int randIndex = rand.nextInt(cards.length);
            Card temp = cards[randIndex];
            cards[randIndex] = cards[i];
            cards[i] = temp;
        }
    }

    public Card getCard(){
        Card card = cards[currentCardPos%20];
        currentCardPos++;
        return card;
    }


}
