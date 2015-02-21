package tillerino.tillerinobot.lang;

import java.util.List;
import java.util.Random;

import org.tillerino.osuApiModel.Mods;
import org.tillerino.osuApiModel.OsuApiUser;

import tillerino.tillerinobot.BeatmapMeta;
import tillerino.tillerinobot.IRCBot.IRCBotUser;
import tillerino.tillerinobot.RecommendationsManager.Recommendation;

public class Default implements Language {

	@Override
	public String unknownBeatmap() {
		return "Ledsen, men jag känner inte igen den här mappen. Den kanske är väldigt ny, väldigt svår, ej rankad eller ej i standard-spelläget.";
	}

	@Override
	public String internalException(String marker) {
		return "Ugh... Det ser ut som att människo-Tillerino har slarvat med mina ledningar."
				+ " Om han inte upptäcker det här snart, kan du [https://github.com/Tillerino/Tillerinobot/wiki/Contact informera honom]? (reference "
				+ marker + ")";
	}

	@Override
	public String externalException(String marker) {
		return "Vad händer? Jag får bara nonsens från osu-servern. Kan du berätta för mej vad det här betyder? 0011101001010000"
				+ " Människo-Tillerino säger att det här inte är något att oroa sej över, och att vi borde pröva igen."
				+ " Om du är super-oroad för någon anledning, kan du [https://github.com/Tillerino/Tillerinobot/wiki/Contact säga till honom] om det. (reference "
				+ marker + ")";
	}

	@Override
	public String noInformationForModsShort() {
		return "ingen data för de begärda modifieringarna";
	}

	@Override
	public void welcomeUser(IRCBotUser user, OsuApiUser apiUser, long inactiveTime) {
		if(inactiveTime < 60 * 1000) {
			user.message("beep boop");
		} else if(inactiveTime < 24 * 60 * 60 * 1000) {
			user.message("Välkommen tillbaka, " + apiUser.getUserName() + ".");
		} else if(inactiveTime > 7l * 24 * 60 * 60 * 1000) {
			user.message(apiUser.getUserName() + "...");
			user.message("...är det där du? Det var så himla länge sen!");
			user.message("Bra att ha dej tillbaka. Kan jag få intressera dej i en rekommendation?");
		} else {
			String[] messages = {
					"du ser ut som att du vill ha en rekommendation.",
					"vad trevligt att få se dej! :)",
					"min favorit-människa! (Säg inte till de andra människorna!)",
					"vilken behaglig överraskning! ^.^",
					"Jag hoppades att du skulle dyka upp. Alla dom andra människorna är astråkiga, men säg inte till dem att jag sa det! :3",
					"vad känner du dej för att göra idag?",
			};
			
			Random random = new Random();
			
			String message = messages[random.nextInt(messages.length)];
			
			user.message(apiUser.getUserName() + ", " + message);
		}
	}

	@Override
	public String unknownCommand(String command) {
		return "okänt kommando \"" + command
				+ "\". Skriv !help om du behöver hjälp!";
	}

	@Override
	public String noInformationForMods() {
		return "Ledsen, jag kan inte få fram någon information om de där modifieringarna just nu.";
	}

	@Override
	public String malformattedMods(String mods) {
		return "De där modifieringarna ser inte rätt ut. Modifieringar kan vara vilken kombination somhelst av DT HR HD HT EZ NC FL SO NF. Kombinera dem utan mellanslag eller speciella tecken. Till exempel: !with HDHR, !with DTEZ";
	}

	@Override
	public String noLastSongInfo() {
		return "Jag kommer inte ihåg att du fick någon låtinformation...";
	}

	@Override
	public String tryWithMods() {
		return "Pröva några modifieringar med den här mappen!";
	}

	@Override
	public String tryWithMods(List<Mods> mods) {
		return "Pröva den här mappen med " + Mods.toShortNamesContinuous(mods);
	}

	/**
	 * The user's IRC nick name could not be resolved to an osu user id. The
	 * message should suggest to contact @Tillerinobot or /u/Tillerino.
	 * 
	 * @param exceptionMarker
	 *            a marker to reference the created log entry. six or eight
	 *            characters.
	 * @param name
	 *            the irc nick which could not be resolved
	 * @return
	 */
	public String unresolvableName(String exceptionMarker, String name) {
		return "Ditt namn förvirrar mej. Är du bannlyst? Om inte, snälla [https://github.com/Tillerino/Tillerinobot/wiki/Contact kontakta Tillerino]. (reference "
				+ exceptionMarker + ")";
	}

	@Override
	public String excuseForError() {
		return "Åh, jag är ledsen, det var den här jättevackra sekvensen av ettor och nollor och jag vart distraherad. Vad var det du ville nu igen?";
	}

	@Override
	public String complaint() {
		return "Din anmälan har lämnats in. Tillerino kommer att kolla det när han snarast kan.";
	}

	@Override
	public void hug(final IRCBotUser user, OsuApiUser apiUser) {
		user.message("Kom hit!");
		user.action("kramar " + apiUser.getUserName());
	}

	@Override
	public String help() {
		return "Hej! Jag är roboten som mördade Tillerino och tog över hans konto! Jag skojade bara, men jag använder hans konto rätt mycket."
				+ " [https://twitter.com/Tillerinobot status och uppdateringar]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki kommandon]"
				+ " - [http://ppaddict.tillerino.org/ ppaddict]"
				+ " - [https://github.com/Tillerino/Tillerinobot/wiki/Contact kontakta mig]";
	}

	@Override
	public String faq() {
		return "[https://github.com/Tillerino/Tillerinobot/wiki/FAQ Vanliga frågor]";
	}
	
	@Override
	public String featureRankRestricted(String feature, int minRank, OsuApiUser user) {
		return "Ledsen, men för tillfället finns " + feature + " bara tillgängligt för spelare som har överträffat rank " + minRank + ".";
	}
	
	@Override
	public String mixedNomodAndMods() {
		return "Vad menar du med nomod med mods?";
	}
	
	@Override
	public String outOfRecommendations() {
		return "Jag har rekommenderat allt jag kan komma på med."
				+ " Försök med andra rekommendations alternativ, eller pröva med !reset. Om du inte är helt säker, skriv !help.";
	}

	@Override
	public String notRanked() {
		return "Ser ut som at den mappen är orankad.";
	}

	@Override
	public void optionalCommentOnNP(IRCBotUser user,
			OsuApiUser apiUser, BeatmapMeta meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public void optionalCommentOnWith(IRCBotUser user, OsuApiUser apiUser,
			BeatmapMeta meta) {
		// regular Tillerino doesn't comment on this
	}

	@Override
	public void optionalCommentOnRecommendation(IRCBotUser user,
			OsuApiUser apiUser, Recommendation meta) {
		// regular Tillerino doesn't comment on this
	}
	
	@Override
	public boolean isChanged() {
		return false;
	}

	@Override
	public void setChanged(boolean changed) {
		
	}

	@Override
	public String invalidAccuracy(String acc) {
		return "Ogiltig noggrannhet: \"" + acc + "\"";
	}

	@Override
	public void optionalCommentOnLanguage(IRCBotUser user, OsuApiUser apiUser) {
		user.message("TheIdiot_ lärde mej hur man pratar svenska.");
	}

	@Override
	public String invalidChoice(String invalid, String choices) {
		return "Ledsen, men \"" + invalid
				+ "\" går inte att kalkylera. Försök med det här: " + choices + "!";
	}

	@Override
	public String setFormat() {
		return "Syntaxen för att ställa en parameter är !set option value. Försök med !help om du behöver mera tips.";
	}
}
