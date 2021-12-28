---
title: System Design
date: "2021-11-23T23:00:00.000Z"
description: ""
---

## System Design Interview - Q&A Structure

1. Clarifying Requirements
2. High Level Architecture
3. Critical Parts

### Example Web Application Arch

- User
- Load balancer layer
- App Server layer
- Backend / Microservices
- Caching layer (or two)
- Persitence later

#### CDN

- TODO Where and why?

#### Load Balancer

- Spread requests across services.
- Many different services
- Or balance between multiple instances of the same service
- Geographical routing, legal reasons.
- Nginx is popular load balancer.
- Option for DNS Load Balancing.
- How do load balancers know how to route traffice?
  - Needs to know availability, health, load
- Load balancing algorithms:
  - Round robin - easiest / simplest option
    - Overloaded servers continue to receive traffic
  - Least connections - monitor open conns
- Statefulness between requests is hard
  - LBs need to forward requests to the same server
    - Can use hash of IP for user.
- What if number of services change?
  - For primitive hash, need to recreate state.
- Consistent hashing
  - Ensure re-mapping has as few active connections as possible
- Can be a single point of failure.

### Database considerations

- What properties should it fulfil?
- SQL vs NoSql, and why?

### General Questions to think about

- What types of data do you have
- Where are they stored, together or separate.
- Rest API between components
  - What is the interface between systems.
  - Why have we separated into services?

### Optimisations and edge cases.

- What kind of users, we care about the most
  - e.g. many millions of followers
- Spiky traffic
- Caching layers
- Pre-computations.
- Improving customer experience with CDN

### Operational topics

- Logging / Metrics / Auditing
- Replication
- Single points of failure
