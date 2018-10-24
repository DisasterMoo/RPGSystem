package com.disastermoo.rpgsystem.core.capabilities.network;

import com.disastermoo.rpgsystem.core.ProxyCommon;
import com.disastermoo.rpgsystem.core.capabilities.IRPGInfo;
import com.disastermoo.rpgsystem.core.capabilities.RPGInfo;
import com.disastermoo.rpgsystem.core.capabilities.RPGInfoProvider;
import com.disastermoo.rpgsystem.core.system.EntityHandler;
import com.disastermoo.rpgsystem.core.system.EntityInfo;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class RPGUpgradeMessage implements IMessage{

	private UpgradeValues upgrade;
	
	public RPGUpgradeMessage(UpgradeValues upgrade)
	{
		this.upgrade = upgrade;
	}
	
	public RPGUpgradeMessage() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.upgrade = new UpgradeValues();
		this.upgrade.pointsSTR = buf.readInt();
		this.upgrade.pointsAGI = buf.readInt();
		this.upgrade.pointsCON = buf.readInt();
		this.upgrade.pointsINT = buf.readInt();
		this.upgrade.pointsWIS = buf.readInt();
		this.upgrade.pointsLCK = buf.readInt();
		this.upgrade.upgradeClass = buf.readBoolean();
		this.upgrade.buySlot = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.upgrade.pointsSTR);
		buf.writeInt(this.upgrade.pointsAGI);
		buf.writeInt(this.upgrade.pointsCON);
		buf.writeInt(this.upgrade.pointsINT);
		buf.writeInt(this.upgrade.pointsWIS);
		buf.writeInt(this.upgrade.pointsLCK);
		buf.writeBoolean(this.upgrade.upgradeClass);
		buf.writeInt(this.upgrade.buySlot);
	}
	
	public static final class UpgradeValues{
		public int pointsSTR, pointsAGI, pointsCON, pointsINT, pointsWIS, pointsLCK;
		public boolean upgradeClass;
		public int buySlot;
	}

	public static final class RPGUpgradeMessageHandler implements IMessageHandler<RPGUpgradeMessage, RPGInfoMessage>{

		@Override
		public RPGInfoMessage onMessage(RPGUpgradeMessage message, MessageContext ctx) {
			if(ctx.side == Side.SERVER) {
				EntityPlayerMP player = ctx.getServerHandler().player;
				IRPGInfo info = ((IRPGInfo)player.getCapability(RPGInfoProvider.RPGINFO_CAP, null));
				if(((RPGInfo)info).Upgrade(message.upgrade, ctx.getServerHandler().player)) {
					EntityHandler.playerUpdateStats(player);
					return null;
					
				}
			}
			return null;
		}

	}

}
