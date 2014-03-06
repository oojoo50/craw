Version 1.0
CREATE  TABLE crew_items (
	'item_id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE 
	, 'name' VARCHAR NOT NULL 
	, 'total_time' INTEGER NOT NULL  DEFAULT 0
	, 'chewing_time' INTEGER NOT NULL  DEFAULT 0
	, 'del_yn' CHAR NOT NULL  DEFAULT 'N'
	, 'prohibited_food' VARCHAR
	, 'create_dt' DATETIME DEFAULT CURRENT_DATE)

CREATE  TABLE meal_time (
	'log_id' INTEGER PRIMARY KEY  NOT NULL  UNIQUE 
	, 'total_time' INTEGER NOT NULL  DEFAULT 0
	, 'log_date' DATETIME DEFAULT CURRENT_DATE
	, 'crew_count' INTEGER DEFAULT 0
	, 'meno' TEXT
	, 'photo_id' INTEGER)

CREATE  TABLE crew_photo (
	'photo_id' INTEGER NOT NULL 
	, 'photo_sql' INTEGER NOT NULL 
	, 'image' BLOB
	, PRIMARY KEY ('photo_id', 'photo_sql'))

CREATE  TABLE config (
	'key' VARCHAR PRIMARY KEY  NOT NULL  UNIQUE 
	, 'value' VARCHAR)