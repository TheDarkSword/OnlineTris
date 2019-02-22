package it.michele.onlinetris.input;

import it.michele.netty.packets.client.CPacketChangeTurn;
import it.michele.netty.packets.client.CPacketStep;
import it.michele.netty.packets.client.CPacketTitle;
import it.michele.onlinetris.ClientHandler;
import it.michele.onlinetris.Game;
import it.michele.onlinetris.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class MouseManager extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
        if(!Game.myTurn) return;
        int x = e.getX();
        int y = e.getY();

        if(mouseOver(x, y, 0, 0, 160, 160)){

            if(Game.cells[0] == null) {
                Game.cells[0] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 0));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 170, 0, 160, 160)){

            if(Game.cells[1] == null) {
                Game.cells[1] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 1));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 340, 0, 160, 160)){

            if(Game.cells[2] == null) {
                Game.cells[2] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 2));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 0, 170, 160, 160)){

            if(Game.cells[3] == null) {
                Game.cells[3] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 3));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 170, 170, 160, 160)){

            if(Game.cells[4] == null) {
                Game.cells[4] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 4));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 340, 170, 160, 160)){

            if(Game.cells[5] == null) {
                Game.cells[5] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 5));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 0, 340, 160, 160)){

            if(Game.cells[6] == null) {
                Game.cells[6] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 6));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 170, 340, 160, 160)){

            if(Game.cells[7] == null) {
                Game.cells[7] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 7));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        } else if(mouseOver(x, y, 340, 340, 160, 160)){

            if(Game.cells[8] == null) {
                Game.cells[8] = Game.getFigure();
                ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, Game.myTurn));
                Game.myTurn = !Game.myTurn;
                ClientHandler.ctx.writeAndFlush(new CPacketStep(Main.ID, 8));
                checkForWin();
                checkForEnemyWin();
                checkForTie();
            }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        return mx > x && mx < x + width && my > y && my < y + height;
    }

    private void checkForWin(){
        if(Game.cells[0] == Game.getFigure()){
            if(Game.cells[1] == Game.getFigure() && Game.cells[2] == Game.getFigure()) Game.won = true;
            else if(Game.cells[3] == Game.getFigure() && Game.cells[6] == Game.getFigure()) Game.won = true;
            else if(Game.cells[4] == Game.getFigure() && Game.cells[8] == Game.getFigure()) Game.won = true;
        }
        if(Game.cells[1] == Game.getFigure() && Game.cells[4] == Game.getFigure() && Game.cells[7] == Game.getFigure()) Game.won = true;
        if(Game.cells[2] == Game.getFigure()){
            if(Game.cells[5] == Game.getFigure() && Game.cells[8] == Game.getFigure()) Game.won = true;
            else if(Game.cells[4] == Game.getFigure() && Game.cells[6] == Game.getFigure()) Game.won = true;
        }
        if(Game.cells[3] == Game.getFigure() && Game.cells[4] == Game.getFigure() && Game.cells[5] == Game.getFigure()) Game.won = true;
        if(Game.cells[6] == Game.getFigure() && Game.cells[7] == Game.getFigure() && Game.cells[8] == Game.getFigure()) Game.won = true;

        if(Game.won){
            ClientHandler.ctx.writeAndFlush(new CPacketTitle(Main.ID, Game.LOSE_STRING, true));
            ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, false));
            Game.titles.put(Game.WON_STRING, true);
            Game.myTurn = false;


        }
    }

    private void checkForEnemyWin(){
        if(Game.cells[0] == Game.getEnemyFigure()){
            if(Game.cells[1] == Game.getEnemyFigure() && Game.cells[2] == Game.getEnemyFigure()) Game.enemyWon = true;
            else if(Game.cells[3] == Game.getEnemyFigure() && Game.cells[6] == Game.getEnemyFigure()) Game.enemyWon = true;
            else if(Game.cells[4] == Game.getEnemyFigure() && Game.cells[8] == Game.getEnemyFigure()) Game.enemyWon = true;
        }
        if(Game.cells[1] == Game.getEnemyFigure() && Game.cells[4] == Game.getEnemyFigure() && Game.cells[7] == Game.getEnemyFigure()) Game.enemyWon = true;
        if(Game.cells[2] == Game.getEnemyFigure()){
            if(Game.cells[5] == Game.getEnemyFigure() && Game.cells[8] == Game.getEnemyFigure()) Game.enemyWon = true;
            else if(Game.cells[4] == Game.getEnemyFigure() && Game.cells[6] == Game.getEnemyFigure()) Game.enemyWon = true;
        }
        if(Game.cells[3] == Game.getEnemyFigure() && Game.cells[4] == Game.getEnemyFigure() && Game.cells[5] == Game.getEnemyFigure()) Game.enemyWon = true;
        if(Game.cells[6] == Game.getEnemyFigure() && Game.cells[7] == Game.getEnemyFigure() && Game.cells[8] == Game.getEnemyFigure()) Game.enemyWon = true;

        if(Game.enemyWon){
            ClientHandler.ctx.writeAndFlush(new CPacketTitle(Main.ID, Game.WON_STRING, true));
            ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, false));
            Game.titles.put(Game.LOSE_STRING, true);
            Game.myTurn = false;
        }
    }

    private void checkForTie(){
        if(Game.won || Game.enemyWon) return;
        for (int i = 0; i < Game.cells.length; i++){
            if(Game.cells[i] == null) return;
        }

        Game.tie = true;
        ClientHandler.ctx.writeAndFlush(new CPacketTitle(Main.ID, Game.TIE_STRING, true));
        ClientHandler.ctx.writeAndFlush(new CPacketChangeTurn(Main.ID, false));
        Game.titles.put(Game.TIE_STRING, true);
        Game.myTurn = false;
    }
}
