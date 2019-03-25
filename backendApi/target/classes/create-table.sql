
-- TODO: Add other created table scripts which are present in java code in ContentDAO


-- Create Thumbnail
create table Thumbnail(content_id varchar(60) NOT NULL,
    url varchar(300) NOT NULL,
    size varchar(12),
    width decimal(6, 2),
    height decimal(6, 2),
    INDEX (size),
    foreign key (content_id) references Contents(content_id)
    );