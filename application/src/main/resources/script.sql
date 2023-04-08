pg_dump -h 14.225.205.235 -p 15432 -U postgres -W -d cadence_visibility -f cadence_visibility.sql
psql -h 14.225.205.235 -p 15432 -U postgres -W -d redmine < redmine-trickster