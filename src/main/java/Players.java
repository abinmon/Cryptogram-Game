package main.java;

import main.java.Player;

import java.io.*;
import java.util.*;

public class Players{
    private List<Player> players;
    private int playersCount;



    public Players()
    {
        players = new LinkedList<>();
        File file = new File("accounts.txt");
        String tempName;
        double tempAccuracy;
        int tempAvg;
        int tempCompleted;
        int tempPlayed;
        playersCount = players.size();


    }
    public void addPlayer(String newName)
    {
        if(newName.contains(" "))
        {
            System.out.println("Names cannot contain a space.");
            return;
        }
        for(Player pl : players)
        {
            if(pl.getUsername().equals(newName))
            {
                System.out.println("main.java.Player already exists.");
                return;
            }
        }

        players.add(new Player(newName, 0, 0, 0, 0));

        playersCount++;



    }
    public void removePlayer(String name)
    {
        for(int i = 0; i< players.size(); i++)
        {
            if(players.get(i).getUsername().equals(name))
            {
                players.remove(i);
                playersCount--;
                return;
            }
        }
    }
    public Player findPlayer(String name)
    {
        for(Player player : players)
        {
            if(player.getUsername().equals(name))
            {
                return player;
            }
        }
        return null;
    }
    public int getPlayerCount()
    {
        return  playersCount;
    }
    public List<Player> getPlayers()
    {
        return players;
    }
    public void savePlayers()
    {

        try
        {
            FileWriter fw = new FileWriter("accounts.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for(Player pl : players)
            {
                out.print(pl.getUsername());
                out.print(" ");
                out.print(pl.getAccuracy());
                out.print(" ");
                out.print(pl.getNumberPlayed());
                out.print(" ");
                out.println(pl.getNumberCompleted());
            }
            out.close();
            bw.close();
            fw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void showTop10()
    {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if(p1.getNumberCompleted() - p2.getAccuracy() > 0)
                {
                    return 1;
                }
                else if(p1.getNumberCompleted() - p2.getAccuracy() > 0){
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        for(int count = 0;count < 10 && count< playersCount; count++)
        {
            System.out.print(players.get(count).getUsername());
            System.out.print(": ");
            players.get(count).displayStats();
        }
    }

}