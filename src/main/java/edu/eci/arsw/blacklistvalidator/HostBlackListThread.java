package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.lang.*;
import java.util.LinkedList;

public class HostBlackListThread extends Thread{

    private HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    private int ocurrencesCount=0;;

    private int ini;
    private int fin;
    private String ipaddress;
    private LinkedList<Integer> blackListOcurrences=new LinkedList<>();

    public HostBlackListThread(int ini, int fin, String ipaddress){
        this.ini = ini;
        this.fin = fin;
        this.ipaddress = ipaddress;
    }


    public void run(){
        for (int i = ini; i <= fin; i++){
            if(skds.isInBlackListServer(i, ipaddress)){
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        }
    }

    public int getOcurrencesCount(){
        return ocurrencesCount;
    }

    public LinkedList getBlackListOcurrence(){
        return blackListOcurrences;
    }

}
