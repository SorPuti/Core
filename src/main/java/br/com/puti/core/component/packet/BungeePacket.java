package br.com.puti.core.component.packet;

import br.com.puti.core.component.packet.util.EncryptionUtil;
import br.com.puti.core.component.packet.util.SerializationUtil;

import java.io.*;
import java.net.Socket;


/**
 * Core of all packets.
 */
public class BungeePacket implements Serializable {
	
	private static final long serialVersionUID = 3728278382368494804L;
	protected BungeePacketType type;
	protected boolean shouldanswer;
	protected String pass = null;
	
	/**
	 * Gets the type of a packet.
	 *
	 * @return type
	 */
	public BungeePacketType getType() {
		return type;
	}
	
	/**
	 * Controls if a packet should answer or not.
	 *
	 * @return true, if successful
	 */
	public boolean shouldAnswer() {
		return shouldanswer;
	}
	
	/**
	 * Displays password if SECMODE is set to SecurityMode.PASS.
	 *
	 * @return pass
	 */
	public String getPassword() {
		return pass;
	}
	
	/**
	 * Sends the packet.
	 *
	 * @return the answer of BungeeBridgeS.
	 */
	public Object send() {
		Socket client;
		Object answer = null;
		if(BungeeBridgeC.SECMODE == SecurityMode.OFF) {
			try {
				client = new Socket(BungeeBridgeC.HOST, BungeeBridgeC.PORT);
				ObjectOutputStream objOUT = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream objIN = new ObjectInputStream(client.getInputStream());
				objOUT.writeObject((Object) this);
				if(this.shouldAnswer()) {
					answer = objIN.readObject();
				}
				objOUT.close();
				objIN.close();
				client.close();
			} catch(InvalidClassException e) {
				ConsolePrinter.err("�4Your version of BungeeBridgeS(Bungeecord) is incompatible to your version of BungeeBridgeC(Spigot)!\n�4You have to update immediately!");
			} catch(IOException e) {
				throw new PacketFailSendException("An IOException occured!\n" + e.getMessage());
//				e.printStackTrace();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}		
		} else if(BungeeBridgeC.SECMODE == SecurityMode.PASS) {
			pass = BungeeBridgeC.PASS;
			try {
				client = new Socket(BungeeBridgeC.HOST, BungeeBridgeC.PORT);
				ObjectOutputStream objOUT = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream objIN = new ObjectInputStream(client.getInputStream());
				objOUT.writeObject((Object) this);
				if(this.shouldAnswer()) {
					answer = objIN.readObject();
				}
				objOUT.close();
				objIN.close();
				client.close();
			} catch(InvalidClassException e) {
				ConsolePrinter.err("�4Your version of BungeeBridgeS(Bungeecord) is incompatible to your version of BungeeBridgeC(Spigot)!\n�4You have to update immediately!");
			} catch(IOException e) {
				throw new PacketFailSendException("An IOException occured!\n" + e.getMessage());
//				e.printStackTrace();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if(BungeeBridgeC.SECMODE == SecurityMode.CIPHER) {			
			try {
				byte[] serialized = SerializationUtil.serialize(this);
				byte[] encoded = EncryptionUtil.encode(serialized, BungeeBridgeC.PASS);
				
				client = new Socket(BungeeBridgeC.HOST, BungeeBridgeC.PORT);
				ObjectOutputStream objOUT = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream objIN = new ObjectInputStream(client.getInputStream());
				objOUT.writeObject((Object) encoded);
				if(this.shouldAnswer()) {
					Object rawanswer = objIN.readObject();
					byte[] decoded = EncryptionUtil.decode((byte[]) rawanswer, BungeeBridgeC.PASS);
					answer = SerializationUtil.deserialize(decoded);
				}
				objOUT.close();
				objIN.close();
				client.close();				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return answer;
	}
} 