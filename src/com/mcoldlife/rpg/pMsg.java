package com.mcoldlife.rpg;

public class pMsg {

	//Error messages
	public static String ERR_NOT_IMPLEMENTED = "�cDiese Funktion ist noch nicht fertig.";
	
	public static String ERR_PLAYER_IN_LAND = "�cDu bist bereits im einem Land.";
	public static String ERR_PLAYER_OWNS_PLOT = "�cDu hast bereits ein Grundst�ck.";
	public static String ERR_PLAYER_NOT_IN_LAND = "�Du geh�rst keinem Land an.";
	public static String ERR_PLAYER_OWNS_NO_PLOT = "�cDu hast kein Grundst�ck.";
	public static String ERR_PLAYER_IN_CITY = "�cDu bist bereits im einer Stadt.";
	public static String ERR_PLAYER_NOT_IN_CITY = "�cDu bist in keiner Stadt.";
	public static String ERR_PLAYER_NOT_OWNER_OF_CITY = "�cDu bist nicht der Besitzer dieser Stadt.";
	public static String ERR_PLAYER_NOT_OWNER_OF_LAND = "�cDu bist nicht der Besitzer dieses Landes.";
	
	public static String ERR_LAND_EXISTS = "�cDas Land existiert bereits.";
	public static String ERR_LAND_NOT_EXISTS = "�cDas Land existiert nicht.";
	public static String ERR_CITY_EXISTS = "�cDiese Stadt existiert bereits.";
	public static String ERR_CITY_NOT_EXISTS = "�cDiese Stadt existiert nicht.";
	
	public static String ERR_CHUNK_HAS_CITY = "�cDer Chunk geh�rt bereits einer Stadt.";
	public static String ERR_CHUNK_HAS_LAND = "�cDer Chunk geh�rt bereits einem Land.";
	public static String ERR_CHUNK_OWNED_BY_OTHER_LAND = "�cDieser Chunk geh�rt einem anderem Land als deinem. Du musst ihn erst einnehmen.";
	public static String ERR_CHUNK_OWNED_BY_SAME_LAND = "�cDieser Chunk geh�rt bereits deinem Land.";
	public static String ERR_CHUNK_OWNED_BY_SAME_CITY = "�cDieser Chunk geh�rt bereits deiner Stadt.";
	
	public static String ERR_POS1_NOT_SET = "�cDie Erste Ecke des Plots wurde nicht gesetzt. �c/plot pos1";
	public static String ERR_POS2_NOT_SET = "�cDie Zweite Ecke des Plots wurde nicht gesetzt. �c/plot pos2";
	public static String ERR_POS_NOT_IN_CITY = "�cDiese Position geh�rt nicht zu der Stadt.";
	public static String ERR_POS_IN_PLOT = "�cDiese Position ist in einem Grundst�ck.";
	public static String ERR_PLOT_EXISTS = "�cEin Grundst�ck mit diesem Namen existiert bereits.";
	public static String ERR_PLOT_NOT_EXISTS = "�cEin Grundst�ck mit diesem Namen existiert nicht.";
	public static String ERR_PLOT_OWNED = "�cDas Grundst�ck geh�rt jemandem.";
	public static String ERR_NOT_INSIDE_PLOT = "�cDu Befindest dich nicht in einem Grundst�ck.";
	
	public static String ERR_MONEY_NOT_ENOUGHT = "�cDu hast zu wenig Geld.";
	public static String ERR_MONEY_NEEDED = "�cDu ben�tigst �6{money}";
	
	public static String ERR_CMD_USAGE_JOB_NOT_FOUND = "�cDer Job �6{job} �cexistiert nicht.";
	
	public static String ERR_CMD_SENDER_NOT_PLAYER = "�cDu musst ein Spieler sein, um diesen Befehl auszuf�hren.";
	public static String ERR_CMD_VALUE_NOT_INT = "�cDer Wert muss eine Zahl sein.";
	
	public static String ERR_CMD_USAGE_CITY = "�c/city <list|create|set|money>";
	public static String ERR_CMD_USAGE_CITY_LIST = "�c/city list [plots]";
	public static String ERR_CMD_USAGE_CITY_CREATE = "�c/city create <name>";
	public static String ERR_CMD_USAGE_CITY_MONEY = "�c/city money";
	public static String ERR_CMD_USAGE_CITY_SET = "�c/city set <tax> <value>";
	
	public static String ERR_CMD_USAGE_LAND = "�c/land <create|join>";
	public static String ERR_CMD_USAGE_LAND_CREATE = "�c/land create <name>";
	public static String ERR_CMD_USAGE_LAND_JOIN = "�c/land join <name>";
	public static String ERR_CMD_USAGE_LAND_LIST = "�c/land list [citys]";
	
	public static String ERR_CMD_USAGE_PLOT = "�c/plot <create|pos|list|claim|unclaim|delete>";
	public static String ERR_CMD_USAGE_PLOT_CREATE = "�c/plot create <name> <price>";
	public static String ERR_CMD_USAGE_PLOT_DELETE = "�c/plot delete <name>";
	public static String ERR_CMD_USAGE_PLOT_SET = "�c/plot set <plotName> <price> <value>";
	public static String ERR_CMD_USAGE_PLOT_POS = "�c/plot pos [1|2]";
	public static String ERR_CMD_USAGE_PLOT_LIST = "�c/plot list [cityName]";
	public static String ERR_CMD_USAGE_PLOT_CLAIM = "�c/plot claim [name]";
	public static String ERR_CMD_USAGE_PLOT_UNCLAIM = "�c/plot unclaim";
	
	public static String ERR_CMD_USAGE_JOB = "�c/job <list|change|info>";
	public static String ERR_CMD_USAGE_JOB_LIST = "�c/job list";
	public static String ERR_CMD_USAGE_JOB_CHANGE = "�c/job change <jobName>";
	public static String ERR_CMD_USAGE_JOB_INFO = "�c/job info [jobName]";
	//Messages
	public static String MSG_CITY_CREATED = "�aDie Stadt �6{city} �awurde erstellt";
	public static String MSG_CITY_MONEY = "�aDie Stadt �6{city} �ahat �6{money} �aGeld.";
	public static String MSG_CITY_SET_TAX = "�aDie Steuer der Stadt �6{city} �awurde auf �6{tax} �agesetzt.";
	
	public static String MSG_LAND_CREATED = "�aDas Land �6{land} �awurde erstellt";
	
	public static String MSG_PLOT_CREATED = "�aDas Grundst�ck �6{plot} �awurde erstellt.";
	public static String MSG_PLOT_DELETED = "�aDas Grundst�ck �6{plot} �awurde gel�scht.";
	public static String MSG_PLOT_SET_PRICE = "�aDas Grundst�ck �6{plot} �akostet nun �6{price} �aGold.";
	public static String MSG_PLOT_CLAIMED = "�aDu hast das Grundst�ck �6{plot} �agekauft.";
	public static String MSG_PLOT_UNCLAIMED = "�aDu hast dein Grundst�ck verkauft.";
	public static String MSG_PLOT_POS_SET = "�aDu hast die Postition �6{pos} �agesetzt.";
	public static String MSG_PLOT_POS_ADDED = "�aDer Positions Stab wurde deinem Inventar hinzugef�gt.";
	
	public static String MSG_JOB_CHANGED = "�aDu hast deinen Job zu �6{job} �age�ndert.";
	
	public static String MSG_EVENT_STOLEN = "�cDu wirst beklaut!";
	
	//Titles
	public static String TITLE_ENTER_CITY = "�aWilkommen in der Stadt";
	public static String TITLE_ENTER_LAND = "�aWilkommen im Land";
	
	//Subtitles
	public static String STITLE_ENTER_CITY = "�6{city}";
	public static String STITLE_ENTER_LAND = "�6{land}";
	
	//Container Title
	public static String CTITLE_PLAYER_INVENTORY = "�c{player} - �6Diebstahl";
}
