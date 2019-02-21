package it.michele.onlinetris;

import io.netty.channel.ChannelHandlerContext;
import it.michele.netty.Client;
import it.michele.netty.NetworkHandler;
import it.michele.netty.packets.Packet;
import it.michele.netty.packets.client.CPacketChangeTurn;
import it.michele.netty.packets.client.CPacketHandshake;
import it.michele.netty.packets.client.CPacketLogin;
import it.michele.netty.packets.client.CPacketStep;
import it.michele.netty.packets.server.SPacketChangeTurn;
import it.michele.netty.packets.server.SPacketHandshake;
import it.michele.netty.packets.server.SPacketLogin;
import it.michele.netty.packets.server.SPacketStep;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Copyright © 2019 by Michele Giacalone
 * This file is part of NettyLib.
 * NettyLib is under "The 3-Clause BSD License", you can find a copy <a href="https://opensource.org/licenses/BSD-3-Clause">here</a>.
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
public class ServerHandler implements NetworkHandler {
    public static Client client1;
    public static Client client2;

    @Override
    public void channelActive(ChannelHandlerContext ctx){

    }

    @Override
    public void processHandshake(Packet packet, ChannelHandlerContext ctx){
        CPacketHandshake in = (CPacketHandshake) packet;

        boolean response = false;
        if(in.getProtocolVersion() == Main.PROTOCOL_VERSION){
            response = true;
        }

        SPacketHandshake out = new SPacketHandshake(response);
        ctx.writeAndFlush(out);
    }

    @Override
    public void processLogin(Packet packet, ChannelHandlerContext ctx){
        CPacketLogin in = (CPacketLogin) packet;

        if(client1 != null && client1.getCode() == in.getCode() || client2 != null && client2.getCode() == in.getCode()){
            ctx.writeAndFlush(new SPacketLogin(false));
            return;
        }

        if(client1 == null){
            client1 = new Client(in.getCode(), in.getName(), ctx);
        } else {
            client2 = new Client(in.getCode(), in.getName(), ctx);
        }

        ctx.writeAndFlush(new SPacketLogin(true));
    }

    @Override
    public void processChangeTurn(Packet packet, ChannelHandlerContext ctx) {
        CPacketChangeTurn in = (CPacketChangeTurn) packet;

        ctx.writeAndFlush(new SPacketChangeTurn(!in.getTurn()));

        if(client1.getCode() == in.getCode()) client2.getCtx().writeAndFlush(new SPacketChangeTurn(in.getTurn()));
        else client1.getCtx().writeAndFlush(new SPacketChangeTurn(in.getTurn()));
    }

    @Override
    public void processStep(Packet packet, ChannelHandlerContext ctx){
        CPacketStep in = (CPacketStep) packet;

        if(client1.getCode() == in.getCode()) client2.getCtx().writeAndFlush(new SPacketStep(in.getCell()));
        else client1.getCtx().writeAndFlush(new SPacketStep(in.getCell()));
    }

    @Override
    public void processTitle(Packet packet, ChannelHandlerContext ctx){

    }

    @Override
    public void processActivation(Packet packet, ChannelHandlerContext ctx) {

    }

    @Override
    public void processList(Packet packet, ChannelHandlerContext ctx) {

    }
}
