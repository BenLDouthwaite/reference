# EC2

## Tasks 

- [ ] Create EC2 instance using CloudFormation
- [ ] Create EC2 instance using AWS CLI 
 
## Summary

- One of the most popular services
- EC2 - Used to launch virtual machines 
- EBS - Store data on virtual drives
- ELB - Distribute load across machines
- ASG - Scaling services across a group
- ENI - Elastic Network Interfaces

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

- On demand : short workload, predicable pricing
  - Pay for what you use. Billed per second. High cost, no upfront payment.
- Reserve instance : over a long time
  - Up to 75% discount compared to on demand. Pay upfront. 
  - Reservation period = 1 year or 3 year. For steady usage apps. 
- Convertible reserve instance : long workloads, flexible instances
  - Can change the EC2 instance type, slightly lower discount
- Schedule reserve instances : 
  - E.g Only for an hour a week, run some weekly cron jobs. 
- Spot instances: bid on instances for short workloads, cheap, can lose instances
  - Up to 90% discount! 
  - Can be out-bit based on supply and demand.
  - Used for batch data jobs, big data analysis, potentially failing workloads
  - Not for critical jobs or DBs
- Dedicated instances : no sharing with others
  - No control of hardware
  - Locked to 1 account
  - No control of placement. Can move instances between hardware.
- Dedicated Hosts : book entire server. Can control instance placement
  - Physical server, full control, full visibility of sockets and cores.
  - 3 year period reservation
  - Useful for complicated licensing models.

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