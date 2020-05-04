# AWS

Notes from the AWS Certified Developer Udemy course.

Target certification date:  16th June 2020

- Regions available around the world. 
-- Regions have availability zones (AZs) 
-- Any address ending with a letter is an AZ
-- AZs are in geographically separate data centres
- AWS Consoles are region scoped

## IAM : Identity and Access management

- IAM is a global service, does not require a region
- Contains Users, groups and roles. Root of security.
- Root should only be used once to setup other roles.
- Policies written in JSON.
-- Policies define what users, groups and roles can do.
- Users receive an account (not root)
-- Users get grouped together. 
-- * 1 user can belong to multiple groups *
-- Roles are for internal usage within AWS - Given to machines.
- MFA can be setup
- Has ~Managed policies~ provided by AWS
- Give minimal permissions needed - Least priviledge principles
- IAM Federation used for large organisations.
-- Allows login using company credentials
-- Uses the SAML standard
- ONE User per physical location
- ONE IAM Role per application

### Creating Users

TODO Repeat this process and document once comfortable
Follow through UI, can assign to groups. Can create a group 'ADMIN'
When a user is added to a group, they will inherit the permissions of the group.

Can set a password policy for all IAM users

We can see the IAM user login in the console, to confirm we are no longer the root user

## EC2

- One of the most popular services
- EC2 - Used to launch virtual machines 
- EBS - Store data on virtual drives
- ELB - DIstribute load across machines
- ASG - Scaling services across a group

### Launch instance : steps

1. Choose an AMI : Amazon Machine Image
	Amazon Linux 2 - Good default choice
2. Select 'type' of machine
	- t2.micro is free-tier eligible
	- use default VPC
	- Subnet : No preference
	- Auto assign public IP
	- When starting an instance, OS has to run somewhere. = 'Storage'
	-- OS Runs in Root storage
	- Tags : KV pairs. 'Name' shows in the UI
3. Configure security
	- Allow SSH
	- 0.0.0.0 is bad practice. easiest to get started.
4. Create a Key-Pair
5. Launch instance.

### Instance state 

- Running
- Stop
- Reboot
- Terminate : Clear all data, completely wiped.

### SSH Access

- The downloaded key will have default permissions '0644'
-- This is 'Too Open', the private key can leak
- To fix, run `chmod 0400 keyfile.pem`
- Then should have correct permissions access `ssh -i keyfile.pem ec2-user@{public-ip}`  
- SSH Access required for instance connect
- * If you get permission error, check key permissions for chmod 0400 *

### Security Groups

- Control inbound and outbound traffic
- Without inbound rule for port 22 for SSH, will simply timout
- Regulate access to ports, IP ranges
- Acts as a firewall, blocked requests never reach the instance
- One security group can be attached to multiple instances.
- Locked to a region
- Good to maintain a security group just for SSH access
- "Connection refused" means SG is correct, and it's an app error.
- Can reference other security groups
- Network timeout = security group misconfiguration (usually)
- Default behavior : deny all inbound traffic, allow all outbound traffic
- * Security groups cannot reference DNS name *

### Private vs Public vs Elastic IP

- AWS supports IPV6
- Public : accessible from anywhere, unique address
- Private : accessible only in private network, unique only to private network.
- Elastic : Public IP will change when stopping and starting instance. 
-- Can only have 5 in your account
-- Try to avoid. Prefer public IP with DNS. OR best is to use a load balancer.
-- Associate to an instance via the console.

### Install Apache on EC2

1. `yum update -y`
2. `yum install -y httpd.x86_64`


### Security Groups

- Control inbound and outbound traffic
- Without inbound rule for port 22 for SSH, will simply timout
- Regulate access to ports, IP ranges
- Acts as a firewall, blocked requests never reach the instance
- One security group can be attached to multiple instances.
- Locked to a region
- Good to maintain a security group just for SSH access
- "Connection refused" means SG is correct, and it's an app error.
- Can reference other security groups

### Private vs Public vs Elastic IP

- AWS supports IPV6
- Public : accessible from anywhere, unique address
- Private : accessible only in private network, unique only to private network.
- Elastic : Public IP will change when stopping and starting instance. 
-- Can only have 5 in your account
-- Try to avoid. Prefer public IP with DNS. OR best is to use a load balancer.
-- Associate to an instance via the console.

### Install Apache on EC2

1. `yum update -y`
2. `yum install -y httpd.x86_64`


### Security Groups

- Control inbound and outbound traffic
- Without inbound rule for port 22 for SSH, will simply timout
- Regulate access to ports, IP ranges
- Acts as a firewall, blocked requests never reach the instance
- One security group can be attached to multiple instances.
- Locked to a region
- Good to maintain a security group just for SSH access
- "Connection refused" means SG is correct, and it's an app error.
- Can reference other security groups

### Private vs Public vs Elastic IP

- AWS supports IPV6
- Public : accessible from anywhere, unique address
- Private : accessible only in private network, unique only to private network.
- Elastic : Public IP will change when stopping and starting instance. 
-- Can only have 5 in your account
-- Try to avoid. Prefer public IP with DNS. OR best is to use a load balancer.
-- Associate to an instance via the console.

### Install Apache on EC2

1. `yum update -y`
2. `yum install -y httpd.x86_64`


### Security Groups

- Control inbound and outbound traffic
- Without inbound rule for port 22 for SSH, will simply timout
- Regulate access to ports, IP ranges
- Acts as a firewall, blocked requests never reach the instance
- One security group can be attached to multiple instances.
- Locked to a region
- Good to maintain a security group just for SSH access
- "Connection refused" means SG is correct, and it's an app error.
- Can reference other security groups

### Private vs Public vs Elastic IP

- AWS supports IPV6
- Public : accessible from anywhere, unique address
- Private : accessible only in private network, unique only to private network.
- Elastic : Public IP will change when stopping and starting instance. 
-- Can only have 5 in your account
-- Try to avoid. Prefer public IP with DNS. OR best is to use a load balancer.
-- Associate to an instance via the console.

### Install Apache on EC2

1. `yum update -y`
2. `yum install -y httpd.x86_64`
3. `systemctl start httpd.service`
4. `systemctl enable httpd.service` - persist service across reboots
5. Ensure security group allows HTTP traffic on port 80
6. Page accessible at publicIP port 80!. Default apache page
7. `echo "Hello World from $(hostname -f)" > /var/www/html/index.html`
8. Refresh the page, and will show 'Hello World'

### EC2 User Data

- Can bootstrap startup with EC2 user script
- Run once at first start
- Automate boot tasks
- Runs with root user
- Con congifure during `Step 3. Configure instance details`

### EC2 Launch Types:

- On demand : short workload, predicatble pricing
-- Pay for what you use. Billed per second. High cost, no upfront payment.
- Reserve instance : over a long time
-- Up to 75% discount compared to on demand. Pay upfront. 
-- Reservation period = 1 year or 3 year. Steady usage apps. 
- Covertible reserve instance : long workloads, flexible instances
-- Can change the EC2 instance type, slightly lower discount
- Schedule reserve instances : 
-- E.g Only for an hour a week, run some weekly cron jobs. 
- Spot instances: bid on instances for short workloads, cheap, can lose instances
-- Up to 90% discount! 
-- Can be outbit based on supply and demand.
-- Used for batch data jobs, big data analysis, potentially failing workloads
-- Not for critical jobs or DBs
- Dedicated instances : no sharing with others
-- No control of hardware
-- Locked to 1 account
-- No control of placement. Can move instances between hardware.
- Decicated Hosts : book entire server. Can control instance placement
-- Physical server, full control, full visibility of sockets and cores.
-- 3 year period reservation
-- Useful for complicated licensing models.

### EC2 Pricing

- Per hour, based on region, instance type, on-demand vs spot etc, linux vs windows 
- Billed by the second, min 60 seconds.
- Storage, data transfer, IP.
- * DO not pay for instance if the instance is stopped. * 
- AMI can be customised, can create out own images ( Custom AMI )
-- Can build custom AMI for windows or linux
-- Allows custom images, faster boot time, machine comes with specific software
-- For security controls
-- Active directory integration
-- Big companies often control their own.
-- * AMI are built for a specific region! *

### EC2 instances overview

- RAM
- CPU
- I/O
- Network
- GPU

- We have .. R/C/P/G/H/X/I/F/Z/CR, and M (balanced)
- T2 and T3 are burstable.
-- Overall, OK CPU performance, can handle a spike in load well. 
-- Will utilise "Burst credits"
-- If credit balance runs out, will remain a weak CPU 
-- T2 unlimited : allows unlimited burst balance.
- ( Not needed to know for the exam )

### Exam checklist

* How to SSH into EC2
* How to manage security groups
* Understand Private / Public / Elastic IP
* How to use User data
* Know we can build custom AMI to enhance OS
* EC2 billed by the second. Easily build and thrown away 'cattle not pets'

## ELB : Elastic Load Balancer. Load Balancing

- Load balancers forward traffic to multiple servers downstream
- Users connect to the load balancer, not the EC2 instance. 
- Want to mask downstream failures
-- Do health checks to see if they are working
- Can provide SSL termination. 
- Enforce stickiness with cookies
- High availability
- Separate public and private traffic.

- ELB is an EC2 Load Balancer
-- AWS guarantees it is working, and takes care of upgrades, a little config provided

- AWS has 3 kinds of load balancers
-- Classic Load Balancer - v1
-- Application Load Balancer - v2
-- Network Load Balancer - v2
--- Recommended to use newer / v2 load balancer. 
- Can setup internal or external ELBs

### Health Checks

- Crucial for load balancers
- Tell load balancer where it can forward requests to
- 200 = Health, otherwise, unhealthy. Setup by developer.

### Application Load Balancer (Layer 7)

- Load balancer across multiple applications across machines (target groups)
- Load balancer many on the same machine (containers)
- Load balance based on route or hostname in url.
-- Separate apps for base port 80, and another for /api
- Very effective for microservices or container based apps.
- Stickiness at target group level.
-- Same user gets the same instance, generated by ALB
- Supports HTTp / HTTPS and Websockets
- App servers don't see the IP of the client directly
-- Client IP inserted into `X-Forwarded-For` header
-- Can also get port `X-Forwarded-Port` and proto `X-Forwarded-Proto`

### Network Load Balncer (Layer 4)

- Forward TCP traffic
- Handle millions of reqs per second
- Low latency
- Used for extreme performance
- Creation process the same as ALB

### General Notes

- Classic Load balancers deprecated
- ALB for HTTP(S) / Websockets
- NLB for TCP
- CLB and ALB support SSL termination
- All have health check
- ALB route on hostname / path
- ALB works well with ECS / Docker
- All have static hosts, do not resolve and use underlying IP
- LBs can scale but not instants - Contact AWS for a "Warm-up"
- NLB directly see the client IP
- 4XX are client errors
- 5XX are app errors.
- 503 = At capacity, no registered target
- If LB cannot connect to app, check security group.

### Steps to setup ALB : TODO 

### Auto Scaling Groups

- Load can change, want to make and destroy servers quickly
- an ASG can 'scale in' (remove old) or 'scale out' (add new) to match demand
- Ensure min and max machines running. Can auto register new instances
- Min Size / Desired Capacity / Max Size
- ASG Have:
-- Launch config: AMI, EC2 User data, EBS volumes, Security Groups, SSH kye Pair, Min/Max/Initial capacity
--- Network + Subnet info
--- Load balancer info
- Alarms can trigger ASG (CloudWatch alarms)
-- Alarms monitor a metric (e.g. average CPU)
-- Matrics are computers for the overall ASG instances)
-- Can create scale-out or scale-in policies. 

- New Rules:
-- Target Avg CPU usage
-- Num Reqs per ELB per insatance, 
-- Avg network in / out.

- Custom Metric:
-- e.g. connected users.
-- Send to CloudWatch using PutMetric API.

- Scaling policy can be CPU, network, metrics, or scheduled.
- Can provide launch config
- Can assign IAM roles to EC2 via ASG
- ASG are free!
- ASG will restart instances if we lose them!
- ASG can terminate instances marked as unhealthy by an LB

### Setup ASG instructions TODO

### EBS Volumes.

- Terminating a machine, will lose root volume. 
- Need to store instance data somewhere. 
- EBS (Elastic Book Store) volume is a network drive to attach to instances
- Allows instances to persist data. 

- Network drive, not a physical drive. 
-- A little latency due to network. 
-- can detach from an instance and attach to another quickly. 
- Locked to an AZ. 
-- To move across, we need to snapshot it. 
- Have provisioned capacity (size in GBs and IOPS)
-- Get billed for provisioned capacity, not usage

- Types:
-- GP2 : General purpose, balanced
-- IO1 : Very high performance and price
-- ST1 : HDD, frequent access, throughput-intensive
-- SC1 : Less frequent access, throughput-intensive

- EBS Volumes resizing
-- Can resize Size or IOPS. 
-- Need to repartition drive. 

- EBS Volumes can be backed up using 'snapshots'
- Snapshots only take the actual space of the blocks.
-- 100GB drive with 5GB or volume, EBS snapshot will be 5GB. 
-- Used for backups, volume migration. 

- EBS Encryption
-- Can create an encrypted EBS volume, encypted inside the volume, all data in fight encrypted. All snapshots are encrypted.
-- All volumes created from the snapshot are encrypted.
-- Encryption and decryption are handled transparently (no action needed)
-- Encryption has minimal impacy on latency
-- Leverages keys from KMS (AES-256)
-- Copying unencrypted snapshot allows encryption

EBS vs Instance Store
- Created without Root EBS volume = Instance store. Root volume is physical. Better IO. Cons, on term store is lost, cannot be restored. Backups operated by user.

BRAIN DUMP
- EBS only on one insatnce at atime
- EBS locked at AZ level
- Migrating EBS Volume = backup (snapshot) and then restore in the other AZ
- EBS Backups use IO, so do not backup when handling a lot of traffic
- Root EBS volumes of instances get terminatee by default is the EC2 instance gets terminated

### Load Balancers and EBS Quiz Questions.:

1. Load balancers provide a static DNS name we can use in our application
2. Load balancers can allow stickiness to avoid re-auth
3. Look into the `X-Forwarded-For` header to view the IP of clients, not the load balancer, from within our applications
4. Enable health checks to ensure users don't see failing pages.
5. Network Load Balancer is the optimum choice for a very high performance application.
6. Application load balancers handle HTTP, HTTPS, Websockets. Network Load balancers handle TCP.
7. ALB redirects based on hostname, or request path
8. * We have an ASG and SLB The ASG will get health checks thanks to ALB. If an instance reports unhealthy, the ASG will terminate the EC2 instance
9. We can create custom CloudWatch metrics to build an alarm to scale the ASG
10. EBS volumes are AZ locked. 
11. EBS Volumes support in flight SSL encryption and do support encryption at rest using KMS.

## Route 53

- Managed DNS
-- A: URL to IPV4
-- AAAA: URL to IPv5
-- CNAME: URL to URL
-- Alias: URL to AWS resource
- Can use public or private domains
-- Provides Load Balancing
-- Limited Health Checks
-- Routing Policy
- Prefer Alias to CNAME for AWS resources (for performance reasons)

## RDS

- Relational Database Service - managed by AWS
- Query with SQL: Postgres, Oracle, MySQL, MariaDB, Oracle, Microsoft SQL Server, Aurora (AWS DB)
-- Has scaling capability
-- Can't SSH into RDS instances
- Can create read replicas for scalability. ( upto 5)
-- Same AZ, cross AZ, or cross region.
-- Replication is ASYNC - Eventually consistent
-- Replicas can be promoted to their own DB
-- Application must update connection string to leverage read replicas
- Multi AZ
-- SYNC Replication (Standby, to separate AZ)
-- Only one DNS exposed to app.
--- Failover from master to standby
-- Increase *Availability*
-- No Manual Intervention
- RDS Backups
-- Automatically enabled.
-- Daily snapshot
-- Transaction logs to restore to any point in time. 
-- 7 Days retention, up to 35 days. 
-- DB Snapchots triggered by the user
- Security
-- Encryption at rest. - with KMS - AES-256
-- SSL Certs to encrypt data in flight
-- To Enforce SSL:
--- Postgres: rds.force_ssl=1 in AWS console
--- MySQL: Within DB `GRANT USAGE ON *.* TO 'mysqluser'@'%' REQUIRE SSL`
-- To Connect using SSL
--- Provide SSL Trust cert (download from AWS)
--- Provide SSL Options when connecting to DB
- Security
-- RDS DB deployed to private subnet
-- Uses security Groups to control who can communicate with RDS
-- IAM policies to control who can manage AWS RDS
-- username and password to login to the db OR use IAM for MySQL / Aurora
- RDS vs Aurora
-- Aurora proprietary from AWS
-- Postgres and MySQL supported by Aurora
-- Aurora is cloud-optimised -> Claims 5x over MYsql, 3x over postgres.
-- Failover, HA native by default.
-- Aurora is NOT free-tier compatible

### RDS - Creation Steps

1. Console -> RDS service -> Databases -> Create Database
2. Select Engine Options e.g. MySQL
3. Selct Template -> Probably Free Tier
4. Choose DB ID , unique across region
5. Set username and password
6. Choose DB instance size (locked for free-tier)
7. Choose Storage
8. Availability, choose if we want a standy db
9. Choose publicly available: usually no, yes for demo to test 

### AWS Elasticache

- ElastiCache is to get managed Redis or Memcached
-- In memory DB, high perf, low latency
-- Reduce load off DBs
-- Make application stateless
-- Write scaling with sharding
-- Read shaling with read replicas
-- MultiAZ with failover
-- AWS managed

- If cache hit - elasticache, if cache miss - RDS, and then write to cache

- User session store
-- User logs into app. 
-- Write session data to elasticache
-- User hits other instance
--- Needs to retrieve session.

- Redis
-- In memory key-value store
-- super low latency
-- cache can survive reboots (persistance)
-- Great to host user sessions, leaderboards, distributed state, relieve pressure off DBs, pub/sub capability
-- Multi AZ
-- Support for read replicas

- Memcached
-- In memory object store
-- Cache does not survive reboots
-- Redis generally preferred. 

### TODO How to setup Redis

### Elasticache strategies

- Helpful for read-heavy apps.
-- Social networks, games, media sharing, Q&A portals
- Computing intensive applications.

- Lazy Loading
-- Load only when necessary
--- If cache hit, use elasticache. If cache miss, read from RDS and THEN populate Elasticache
- + Only requested data is cached. No unused data
- + Node failures not fatal
- (-) Cache miss penalty is quite big
- (-) Data can be updated in DB and not in cache, stale data. 
-- Can have TTL

- Write Through
-- Update cache whenever DB is updated. 
--- Write to RDS first, then to cache. 
- (+) Data never stale
- (+) Now write penalty, 2 calls each time. 
- (-) Missing data is added / updated in the DB. Mitigation is to implement Lazy Loading too.

### AWS VPC

- Can create a VPC in a region
- Each VPC has subnets
- Each subnet must be mapped to an AZ
- Common to have a public and a private subnet.
- Public subets usually has:
-- Load balancers, static websites, files, public auth layers
- Private subnets usually contain:
-- Web app servers
-- Databases etc
- Public anc private can communicate in the same VPC
- All new accounts have a default VPC
- Can use a VPN to connect to the VPC
- VPC flow logs allow you to monitor the traffic within, and out of your VPC
- VPC are per region. 
- Subnets are VPC per AZ
- Some AWS resources can be deployed in VPC while others can't
- Can peer VPC within or across accounts to make it look like they're the same network. 

## 3 Tier Architecture

- Route 53 maps URL to load balancer using an alias record. 
- Load balancer forwards onto apps within an autoscaling group in multiple AZ.
-- Load balancer is in the public subnet
- Each app can make request to elasticache (redis), or to RDS. Where RDs can have slave replication.
- End user only accepts the load balancer. 

## Elastic Beanstalk

- A developer centric view of deploying an app on AWS
- Free,only pay for the underlying instances
- Only application code is the responsibility of the dev
- Three architecture
-- Single instance dev : good for dev
-- LB + ASG : Great for production and pre-production web applications
-- ASG only : great for non-web apps
- EBS has 3 components 
-- Application
-- Application Version
-- Environment name
- Can deploy versions, and deploy between environemnts
- Can rollbacl to previous version.
- Create app, create enc, upload version, release
- Supports many platforms (inc Node, Java, Go)

## CloudFormation

- Resources are mandatory, core of the template
- 224 different resources, need to know how to refernce the docs to use them
- * Key way to define infrastructure as code * 
