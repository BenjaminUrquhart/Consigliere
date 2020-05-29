# IMPORTANT: with the new Town Traitor gamemode comes a change in how the game client communicates with the server. Due to security updates, to record games, the tool can no longer passively listen to network traffic. It must instead intercept it and forward it to the server. As of Unity update 3.2 (released May 28th, 2020), Consigliere will no longer correctly parse packets until a fix is implemented.

# UPDATE: it appears the new connection method only applies to web. Steam and mobile still operate the same way. For now, I will be dropping support for the web client.

# Consigliere
Record your Town of Salem sessions for post-game analysis.

This program requires a TrueColor compatible terminal:
- On MacOS/OSX, I recomend iTerm
- On Windows, MingW64 or similar (Git Bash for example)
- On Linux, your terminal should already support it


Run with no arguments for live capturing of sessions:

`java -jar Consigliere.jar`

Run with the --file argument to load a capture file for analysis:

`java -jar Consigliere.jar --file path/to/mycapture.pcap[ng]`

# Dependencies

**Note:** I am working on packaging these dependencies into the program so you don't need to install them manually.

This project relies on `libpcap` for capturing packets. You will need to install it in order to use Consigliere:
- Windows: Follow [these installation instructions](https://github.com/kaitoy/pcap4j#winpcap-or-npcap). You may need administrative rights in order to start capturing.
- MacOS: The preinstalled version is enough to read data. To capture traffic, you will need the ChmodBPF package packaged with Wireshark. Download [Wireshark](https://www.wireshark.org/download.html) and run the `Install ChmodBPF` package.
- Linux: Use your package manager to install `libpcap-dev`. Then, run `sudo setcap cap_net_raw,cap_net_admin=eip /path/to/java` to grant non-root users permission to capture data.

# Help Needed
**Note:** when capturing data for these roles, it is recommended to run Wireshark or a similar tool alongside Consigliere to avoid data loss if Consigliere crashes. If a crash occurs, send the stack trace along with the capture from Wireshark.

I would greatly appriciate game captures for the following roles:

**Classic:**
- Lookout (with visits)
- ~~Transporter~~
- ~~Witch~~
- ~~Mayor (You must be the mayor. Preferably with a reveal)~~
- Jailor (Executed Townie only)
- Vigilante (Death from guilt only)
- Vampire Hunter (Vampire chat and/or conversion to Vigilante)
- Jester (Lynched - you must be the Jester)
- ~~Executioner (Preferably with a successful lynch)~~ I got a game, working on it.

**Coven DLC:**
- Guardian Angel
- Coven Leader (You must be Coven, but CANNOT be the CL)
- Hex Master
- Potion Master (Prefereably you being the PM. Please tell me which potions were used)
- ~~Tracker (with visits)~~
- ~~Trapper~~
- ~~Pirate~~

# Known Bugs
- Potion Master potions are wrong
- Necromancer will always start the night by "changing their mind"

