import com.grafos.lib.DatabaseManager;
import com.grafos.model.Configuration;

public class tester {

	public static void main(String[] args) {
		DatabaseManager dm = new DatabaseManager();
		dm.createConfig(new Configuration("folder","sucess","error",false));

	}

}
