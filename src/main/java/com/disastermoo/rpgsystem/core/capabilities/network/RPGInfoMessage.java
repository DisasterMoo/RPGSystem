package com.disastermoo.rpgsystem.core.capabilities.network;

import com.disastermoo.rpgsystem.RPGSystem;
import com.disastermoo.rpgsystem.core.ProxyClient;
import com.disastermoo.rpgsystem.core.RegistryHandler;
import com.disastermoo.rpgsystem.core.system.EntityInfo;
import com.disastermoo.rpgsystem.core.system.Class;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class RPGInfoMessage implements IMessage{

	EntityInfo send;
	
	public RPGInfoMessage(EntityInfo send)
	{
		this.send = send;
	}
	
	public RPGInfoMessage() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		send = new EntityInfo();
		for(int i = 0; i < 7; i++)
		{
			int value = buf.readInt();
			if(value > -1) {
				send.setMateriaSlotEnabled(i, true);
				if(value > 0) {
					ItemStack inSlot = new ItemStack(RegistryHandler.INSTANCE.items.materia, 1, value);
					send.setMateriaInSlot(i, inSlot);
				}else {
					send.setMateriaInSlot(i, null);
				}
			}else {
				send.setMateriaSlotEnabled(i, false);
			}
		}
		send.getAttributes().setSTR(buf.readInt());
		send.getAttributes().setAGI(buf.readInt());
		send.getAttributes().setCON(buf.readInt());
		send.getAttributes().setINT(buf.readInt());
		send.getAttributes().setWIS(buf.readInt());
		send.getAttributes().setLCK(buf.readInt());
		send.setClassType(Class.Type.getTypeByID(buf.readInt()));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		for(int i = 0; i < 7; i++)
		{
			if(send.isMateriaSlotEnabled(i)) {
				ItemStack inSlot = send.getMateriaInSlot(i);
				if(inSlot != null)
					buf.writeInt(send.getMateriaInSlot(i).getMetadata());
				else
					buf.writeInt(0);
			}else {
				buf.writeInt(-1);
			}
		}
		buf.writeInt(send.getAttributes().getSTR());
		buf.writeInt(send.getAttributes().getAGI());
		buf.writeInt(send.getAttributes().getCON());
		buf.writeInt(send.getAttributes().getINT());
		buf.writeInt(send.getAttributes().getWIS());
		buf.writeInt(send.getAttributes().getLCK());
		int classID = 0;
		if(send.getClassType() != null)classID = send.getClassType().getID();
		buf.writeInt(classID);
	}

	public static final class RPGInfoMessageHandler implements IMessageHandler<RPGInfoMessage, IMessage>{

		@Override
		public IMessage onMessage(RPGInfoMessage message, MessageContext ctx) {
			if(ctx.side == Side.CLIENT) {
				((ProxyClient)RPGSystem.proxy).setClientInfo(message.send);
				((ProxyClient)RPGSystem.proxy).markUpdate(false);
			}
			return null;
		}

	}

}
