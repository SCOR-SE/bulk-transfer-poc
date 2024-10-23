# Bulk Transfer Draft


## Table of Contents

- [Overview](#overview)
- [Architecture Diagram](#architecture-diagram)
- [Azure Services Used](#azure-services-used)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Clone the Repository](#clone-the-repository)
- [Setting Up Environment Variables](#setting-up-environment-variables)
- [Building and Deploying the Azure Functions](#building-and-deploying-the-azure-functions)
- [Service Bus Setup](#service-bus-setup)
- [Creating Topics and Subscriptions](#creating-topics-and-subscriptions)
- [Applying Subscription Filters](#applying-subscription-filters)
- [Running the Test Applications](#running-the-test-applications)
- [Cleanup Function](#cleanup-function)
- [Dead-Letter Handling](#dead-letter-handling)
- [TODO: Azure CLI Commands](#todo-azure-cli-commands)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project demonstrates how to integrate Azure Blob Storage, Event Grid, Azure Functions, Azure Table Storage, Azure queue storage, and Azure Service Bus, to build a managed file transfer service (MFT). It includes:

- **Producers**: Upload files to Azure Blob Storage using azcopy, passing some identification information.
- **Event Grid**: Triggers an Azure Function when a blob is created.
- **Azure Function (EventGridMetadataLoggerFunction)**: Processes the event, extracts metadata, logs it to Azure Table Storage, and sends a message to an Azure Service Bus Topic.
- **Service Bus**: Routes messages to different subscriptions based on filters.
- **Consumers**: Java applications that consume messages from their respective subscriptions.
- **Cleanup Function**: Periodically deletes expired entries from Azure Table Storage.
- **Dead-Letter Handling**: Manages failed events by sending them to a dead-letter queue for further processing.

## Architecture Diagram

![Mermaid Diagram](https://github.com/farinazgh/bulk-transfer-poc/raw/main/mermaid-diagram.svg)


## Azure Services Used

- **Azure Blob Storage**: Stores files uploaded by producers.
- **Azure Event Grid**: Triggers events when blobs are created in Azure Blob Storage.
- **Azure Functions**: Hosts the EventGridMetadataLoggerFunction and CleanupFunction.
- **Azure Table Storage**: Stores metadata about processed files and events.
- **Azure Storage Queues**: Used for dead-lettering failed events.
- **Azure Service Bus**: Manages messaging between the function and consumers, using topics and subscriptions with filters.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 
- Azure CLI 
- 
### Run

`mvn clean package
mvn azure-functions:delpoy`

### AZCopy and checksum generation

`producer.sh`
### Creating Topics and Subscriptions and Filters

TODO: Add the Azure CLI commands to create Service Bus topics and subscriptions.

`setup_service_bus_subscription.sh`

### Applying Subscription Filters

TODO: Add the Azure CLI commands to apply filters to the subscriptions.


