package za.co.typespeed.completition.Server;


public class RunChosenServer {

    private String chosenSever;

    public RunChosenServer() {
//
//        do{
////            chosenSever = Server.getInput("Which server you want to run? {api or our} ");
//            if(!chosenSever.equalsIgnoreCase("api")){
//
//            }
//        }while (!chosenSever.equalsIgnoreCase("api") && !chosenSever.equalsIgnoreCase("our"));

    }

    public void startChosenServer(){

        MultiServer.socketServer();

//        if(chosenSever.equalsIgnoreCase("api")){
////            WebServer server = new WebServer();
////            server.start(Port.getPortNumber());
//        }else if (chosenSever.equalsIgnoreCase("our")){
//            socketServer();
//        }
    }

}
