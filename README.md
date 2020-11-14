# As of now, web is unsupported because it connects over websockets (as opposed to raw sockets)

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
- Jailor (Executed Townie only)
- Vigilante (Death from guilt only)

**Coven DLC:**
- Pirate - there are some strange things happening with duels

# Known Bugs
- Retributionist's secondary message will not inherit the action verb of their zombie (defaults to "target")

