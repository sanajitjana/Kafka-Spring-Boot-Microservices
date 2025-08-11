# Event-Driven Microservices System with Apache Kafka

A scalable event-driven microservices architecture that enables real-time order processing and inventory management using Apache Kafka for reliable message streaming and event handling.

This project implements a distributed system of microservices that communicate asynchronously through Kafka events. The architecture consists of four core services - Order, Inventory, Payment and Notification - that work together to process customer orders while maintaining data consistency and system reliability.

The system leverages Apache Kafka's pub/sub messaging to decouple services and enable independent scaling. Key features include:
- Event-driven architecture with Kafka for reliable message streaming
- Containerized microservices using Docker and Docker Compose
- Real-time order status updates and inventory management
- Kafdrop UI for Kafka topic monitoring and management
- Configurable services with environment-based settings

## Repository Structure
```
.
├── docker-compose.yml          # Main Docker Compose configuration for all services
├── kafka-infra/               # Kafka infrastructure setup
│   └── docker-compose.yml     # Kafka and Zookeeper configuration
├── order-service/             # Order processing service
│   ├── src/                   # Source code for order management
│   └── Dockerfile            # Container build instructions
├── inventory-service/         # Inventory management service
│   ├── src/                   # Source code for inventory tracking
│   └── Dockerfile            # Container build instructions
├── payment-service/           # Payment processing service
│   └── src/                   # Source code for payment handling
└── notification-service/      # Notification handling service
    └── src/                   # Source code for notifications
```

## Usage Instructions
### Prerequisites
- Java Development Kit (JDK) 17 or later
- Docker and Docker Compose
- Maven 3.6.x or later
- Git

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd <repository-name>
```

2. Build the services:
```bash
# Build each service
cd order-service && ./mvnw clean package
cd ../inventory-service && ./mvnw clean package
cd ../payment-service && ./mvnw clean package
cd ../notification-service && ./mvnw clean package
```

3. Start the infrastructure:
```bash
docker-compose up -d
```

### Quick Start

1. Verify services are running:
```bash
docker-compose ps
```

2. Access Kafdrop UI:
- Open http://localhost:9000 in your browser
- View Kafka topics and messages

3. Create a sample order:
```bash
curl -X POST http://localhost:8081/api/orders \
  -H "Content-Type: application/json" \
  -d '{"orderId":"123","userId":"user1","status":"PENDING"}'
```

### More Detailed Examples

1. Monitor order processing:
```bash
# Watch Kafka topics
docker-compose logs -f kafka

# Check inventory service logs
docker-compose logs -f inventory-service
```

2. Scale services:
```bash
# Scale inventory service
docker-compose up -d --scale inventory-service=2
```

### Troubleshooting

Common Issues:

1. Services fail to start
- Check if Kafka is running: `docker-compose ps`
- Verify ports are not in use: `netstat -an | grep PORT`
- Review service logs: `docker-compose logs SERVICE_NAME`

2. Messages not being processed
- Check Kafka topics in Kafdrop UI
- Verify consumer group status
- Review consumer service logs

3. Connection issues
- Ensure all containers are on same network
- Verify bootstrap server configuration
- Check network connectivity between services

## Data Flow

The system processes orders through a series of event-driven interactions between services, maintaining data consistency through Kafka events.

```ascii
[Order Service] --> (order-created) --> [Kafka] --> [Inventory Service]
                                        |
                                        +--> [Payment Service]
                                        |
                                        +--> [Notification Service]
```

Component Interactions:
1. Order Service publishes order events to Kafka
2. Inventory Service consumes order events and updates stock
3. Payment Service processes payment for valid orders
4. Notification Service sends updates to relevant parties
5. All services communicate asynchronously through Kafka topics
6. Each service maintains its own data store
7. Kafdrop provides monitoring and management UI

## Infrastructure

![Infrastructure diagram](./docs/infra.svg)

Kafka Infrastructure:
- Zookeeper: Coordination service (port 2181)
- Kafka Broker: Message broker (port 9092)
- Kafdrop: Web UI for Kafka monitoring (port 9000)

Application Services:
- Order Service: Port 8081
- Inventory Service: Port 8082
- Payment Service: Internal
- Notification Service: Internal

## Deployment

Prerequisites:
- Docker environment
- Network access to container registry
- Sufficient system resources

Deployment Steps:
1. Configure environment variables
2. Deploy Kafka infrastructure
3. Deploy application services
4. Verify service health
5. Monitor system metrics