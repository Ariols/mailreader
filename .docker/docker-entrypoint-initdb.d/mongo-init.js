db = db.getSiblingDB('mailreader');
db.createUser(
  {
    user: 'mailreader',
    pwd: 'mailreader',
    roles: [{ role: 'readWrite', db: 'mailreader' }],
  },
);
db.createCollection('mail');
