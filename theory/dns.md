# DNS - Domain Name System

In attempting to setup some example sites, I had some domains but managed to mess up their config.
I put off fixing them for too long, because I didn't understand enough and would get frustrated.

Should learn this properly, after not using it for years!

## Word vomit. 
// TODO Clean into actual useful paragraphs.

* Convert domain names into IP addresses.
* The registrar is the authoritative source for DNS queries.
* Every DNS record has a name, type, value and TTL.
* Can use `dig` command to query DNS records
* Route53 is just amazon's DNS management
* *Must make Route53 authoritative*
    * Buy from Route 53
    * Transfer domain to Route 53's registrar
    * Update NS records in existing registrar
    
### Route 53

* Hosted Zones and DNS records
* 'A' specifies IPV4 address
* 'AAAA' specifies IPV6 address
* 'CNAME' specified another CNAME, A, or AAAA DNS record
    * 'CNAME' is like a redirect.
    
* Route 53 allows aliad records
    * Acts as a symlink
  
#### Matching Name Server Config

In one example, my Domain and Hosted zone Name Servers were not matching, meaning my requests were not routed