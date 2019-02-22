package it.michele.onlinetris;

import it.michele.onlinetris.display.Display;
import it.michele.onlinetris.gfx.Assets;
import it.michele.onlinetris.input.MouseManager;
import it.michele.onlinetris.states.GameState;
import it.michele.onlinetris.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Copyright © 2019 by Michele Giacalone
 * This file is part of OnlineTris.
 * OnlineTris is under "The 3-Clause BSD License", you can find a copy <a href="https://opensource.org/licenses/BSD-3-Clause">here</a>.
 * <p>
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
public class Game implements Runnable {

    private Display display;

    private int width, height;
    private String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private State gameState;
    private State menuState;

    private MouseManager mouseManager;

    private Font font = new Font("Verdana", Font.BOLD, 32);
    private Font smallerFont = new Font("Verdana", Font.BOLD, 20);
    private Font largerFont = new Font("Verdana", Font.BOLD, 50);

    private String waitingString = "Aspettando un altro giocatore";
    //private String unableToCommunicateWithOpponentString = "Impossibile comunicare con il server.";
    public static final String WON_STRING = "Hai vinto!";
    public static final String LOSE_STRING = "Hai Perso!";
    public static final String TIE_STRING = "Gioco finito in pareggio";

    public static HashMap<String, Boolean> titles = new HashMap<>();
    public static BufferedImage cells[] = new BufferedImage[9];
    public static boolean myTurn = true;
    public static boolean won = false;
    public static boolean enemyWon = false;
    public static boolean tie = false;

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        this.mouseManager = new MouseManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getCanvas().addMouseListener(mouseManager);
        Assets.init();

        gameState = new GameState(this);

        State.setState(gameState);
    }

    private void tick(){

        if(State.getState() != null){
            State.getState().tick();
        }
    }

    private void render(){
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if(bufferStrategy == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();

        //Clear Screen
        graphics.clearRect(0, 0, width, height);

        //Start Drawing

        if(State.getState() != null){
            State.getState().render(graphics);

            for(String title : titles.keySet()){
                if(titles.get(title)){
                    graphics.setColor(Color.MAGENTA);
                    graphics.setFont(font);
                    Graphics2D graphics2D = (Graphics2D) graphics;
                    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    int stringWidth = graphics2D.getFontMetrics().stringWidth(title);
                    graphics.drawString(title, 250 - stringWidth / 2, 250);
                }
            }
        }
        //End Drawing

        bufferStrategy.show();
        graphics.dispose();
    }

    public void run(){
        init();

        int ticks = 60;

        double tickPerSecond = 1000000000 / ticks;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / tickPerSecond;
            lastTime = now;

            if(delta >= 1){
                tick();
                delta--;
            }

            render();
        }

        stop();
    }

    public synchronized void start(){
        if(running) return;
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop(){
        if(!running) return;
        try{
            thread.join();
            running = false;
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static BufferedImage getFigure(){
        return Assets.blueX;
    }

    public static BufferedImage getEnemyFigure(){
        return Assets.redCircle;
    }
}
