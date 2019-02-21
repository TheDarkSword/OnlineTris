package it.michele.onlinetris.states;

import it.michele.onlinetris.Game;
import it.michele.onlinetris.gfx.Assets;

import java.awt.*;

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
public class GameState extends State {

    public GameState(Game game){
        super(game);

    }

    @Override
    public void tick(){

    }

    @Override
    public void render(Graphics graphics){
        graphics.drawImage(Assets.board, 0, 0, null);

        if(Game.cells[0] != null){
            graphics.drawImage(Game.cells[0], 0, 0, null);
        }
        if(Game.cells[1] != null){
            graphics.drawImage(Game.cells[1], 170, 0, null);
        }
        if(Game.cells[2] != null){
            graphics.drawImage(Game.cells[2], 340, 0, null);
        }
        if(Game.cells[3] != null){
            graphics.drawImage(Game.cells[3], 0, 170, null);
        }
        if(Game.cells[4] != null){
            graphics.drawImage(Game.cells[4], 170, 170, null);
        }
        if(Game.cells[5] != null){
            graphics.drawImage(Game.cells[5], 340, 170, null);
        }
        if(Game.cells[6] != null){
            graphics.drawImage(Game.cells[6], 0, 340, null);
        }
        if(Game.cells[7] != null){
            graphics.drawImage(Game.cells[7], 170, 340, null);
        }
        if(Game.cells[8] != null){
            graphics.drawImage(Game.cells[8], 340, 340, null);
        }
    }
}
