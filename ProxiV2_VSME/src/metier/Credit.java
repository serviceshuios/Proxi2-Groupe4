package metier;

public class Credit {

	private Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Credit(Client client) {
		super();
		this.client = client;
	}

	public Credit() {
		super();
	}

	@Override
	public String toString() {
		return "Credit [client=" + client + "]";
	}
	

}
