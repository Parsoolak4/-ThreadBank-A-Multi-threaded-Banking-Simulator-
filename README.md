# ThreadBank: A Multi-threaded Banking Simulator 🏦

## 📌 Overview
ThreadBank is a Java application that simulates a concurrent banking system using multiple threads to handle client transactions, network operations, and server-side processing. The system demonstrates key concepts in concurrent programming including thread synchronization, buffer management, and client-server communication.

## 🏗️ Architecture
- **Multi-threaded Design**: Implements separate threads for network management, server operations, and client transactions
- **Buffer Management**: Features circular buffers for managing network I/O operations
- **Transaction Processing**: Handles multiple transaction types (DEPOSIT, WITHDRAW, QUERY) concurrently
- **File-based Storage**: Utilizes text files for account information and transaction records

## 🧩 Core Components
### Network (`Network.java`)
- Manages network communication
- Handles buffer operations
- Controls client-server connectivity

### Server (`Server.java`)
- Processes transactions
- Manages account operations
- Handles concurrent requests

### Client (`Client.java`)
- Sends transaction requests
- Receives transaction results
- Manages client-side operations

### Supporting Classes
- `Accounts.java`: Account structure and operations
- `Transactions.java`: Transaction data structure
- `Driver.java`: Main application entry point

## ⚙️ Technical Specifications
- **Language**: Java
- **Architecture**: Multi-threaded
- **Buffer Type**: Circular buffer
- **Storage**: File-based
- **Thread Types**: Network, Server, Client (Sender/Receiver)

## 🛠️ Configuration
| Parameter | Value | Description |
|-----------|-------|-------------|
| Buffer Size | 10 packets | Configurable for performance |
| Max Accounts | 100 | Maximum number of accounts |
| Max Transactions | 100 | Maximum pending transactions |

## 📊 Performance
The system's performance can be optimized by adjusting the buffer size. Current settings:
- Default buffer size: 10 packets
- Larger buffer = More simultaneous transactions
- Trade-off between memory usage and throughput

## 📂 File Structure
