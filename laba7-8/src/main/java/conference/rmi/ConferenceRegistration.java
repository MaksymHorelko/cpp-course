package conference.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConferenceRegistration extends Remote {
	public int registerParticipant(Participant participant) throws RemoteException;

	public void clientConnected() throws RemoteException;

	public void clientDisconnected() throws RemoteException;
}
