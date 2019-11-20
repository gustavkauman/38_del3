package IOOuterActive;

public class Game {

    private CardBundle cardBundle = new CardBundle();
    private Player[] players;
    private DiceCup diceCup = new DiceCup(1, 6);
    private Player currentPlayer;
    private boolean noLoser = true;






    public void playGame(){
        initializeGame();

        while(noLoser){
            //Game logic

            //TODO: Check for Jailed.
            //TODO: Check for PlayerSpecificCard



            for(Player player : players){
                if(player.isFallit()){
                    noLoser = false;
                }
            }
        }

        //Find og udskriv vinder

    }

    private void initializeGame() {
        cardBundle.createCards();

    }


    private void drawCard(){
        Card drawnCard = cardBundle.getCard();
        System.out.println(drawnCard.getText());

        switch (drawnCard.getName()){
            case "GiveToCar":
                players[0].setPlayerSpecificCard(true);
                drawCard();
                break;
            case "GiveToShip":
                players[1].setPlayerSpecificCard(true);
                drawCard();
                break;
            case "GiveToCat":
                if(players.length > 2)
                    players[2].setPlayerSpecificCard(true);
                drawCard();
                break;
            case "GiveToDog":
                if(players.length > 3)
                    players[3].setPlayerSpecificCard(true);
                drawCard();
                break;
            case "GoToStart":
                checkStartPassed(24 - currentPlayer.getCurrentField());
                break;
            case "Move5":
                int chosenNumberOfMoves;
                //TODO: int chosenNumberOfMoves = guiController ...
                //checkStartPassed(chosenNumberOfMoves);
                //TODO: Udfør feltets funktion
                break;
            case "OrangeFree":

                break;
            case "MoveOrCard":
                boolean move = false;
                //TODO: move = guiController getUserSelection ...
                if(move){
                    checkStartPassed(1);
                    //TODO: Udfør feltets funktion
                } else {
                    drawCard();
                }
                break;
            case "Overeating":
                currentPlayer.addMoney(-2);
                break;
            case "OrangeOrGreenFree":

                break;
            case "LightblueFree":

                break;
            case "GetOutOfJail":
                currentPlayer.setJailCards(currentPlayer.getJailCards() + 1);
                break;
            case "MoveToStrand":
                checkStartPassed(23 - currentPlayer.getCurrentField());
                //TODO: Udfør feltets funktion
                break;
            case "Birthday":
                for(Player player : players){
                    if(player != currentPlayer){
                        player.addMoney(-1);
                        currentPlayer.addMoney(1);
                    }
                }
                break;
            case "PinkOrDarkblueFree":

                break;
            case "Homework":
                currentPlayer.addMoney(2);
                break;
            case "RedFree":

                break;
            case "Skatepark":
                int movedFields;
                if(currentPlayer.getCurrentField() < 10){
                    movedFields = 10 - currentPlayer.getCurrentField();
                } else {
                    movedFields = 24 - (currentPlayer.getCurrentField() - 10);
                }
                checkStartPassed(movedFields);
                //TODO: Udfør feltets funktion.
                break;
            case "LightblueOrRedFree":

                break;
            case "BrownOrYellowFree":

                break;
        }






    }

    private void checkStartPassed(int fieldsMoved) {
        if(currentPlayer.getCurrentField() + fieldsMoved < 24){
            currentPlayer.setCurrentField(currentPlayer.getCurrentField() + fieldsMoved);
            //TODO: Update GUI
        } else {
            System.out.println("Du passerede START og modtager $2.");
            currentPlayer.addMoney(2);
            currentPlayer.setCurrentField(currentPlayer.getCurrentField() + fieldsMoved - 24);
            //TODO: Update GUI
        }
    }

}
