ALTER TABLE book_text
    ADD CONSTRAINT uc_book_text_book UNIQUE (book_id);

ALTER TABLE book
    ADD CONSTRAINT uc_book_title UNIQUE (title);

ALTER TABLE quote
    ADD CONSTRAINT uc_quote_quote_text UNIQUE (quote_text);

ALTER TABLE book_tag
DROP
CONSTRAINT book_tag_pkey;

ALTER TABLE quote
DROP
COLUMN page_number;

ALTER TABLE book_text
    ALTER COLUMN book_id DROP NOT NULL;

ALTER TABLE quote
    ALTER COLUMN book_id DROP NOT NULL;

ALTER TABLE book_text
DROP
COLUMN content;

ALTER TABLE book_text
    ADD content OID NOT NULL;

ALTER TABLE book
ALTER
COLUMN description TYPE VARCHAR(500) USING (description::VARCHAR(500));

ALTER TABLE quote
ALTER
COLUMN quote_text TYPE VARCHAR(255) USING (quote_text::VARCHAR(255));

ALTER TABLE tag
ALTER
COLUMN tag_name TYPE VARCHAR(255) USING (tag_name::VARCHAR(255));

ALTER TABLE book
ALTER
COLUMN title TYPE VARCHAR(100) USING (title::VARCHAR(100));