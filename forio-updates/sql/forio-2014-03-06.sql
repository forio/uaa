alter table groups 
  add column organization VARCHAR(255) null,
  add column event VARCHAR(255) null,
  add column eventDate TIMESTAMP null;
