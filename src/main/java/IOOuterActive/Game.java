package IOOuterActive;

public class Game {

    private CardBundle cardBundle = new CardBundle();
    private Player[] players;
    private DiceCup diceCup = new DiceCup(1, 6);







    public void playGame(){
        initializeGame();

    }

    private void initializeGame() {
        cardBundle.createCards();

    }


    private void drawCard(){
        Card drawnCard = cardBundle.getCard();

        switch (drawnCard.getName()){
            case "GiveToCar":
                //Insert logic here
                break;




















        }







    }

}
