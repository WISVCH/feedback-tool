ALTER TABLE feedback
  ADD COLUMN handled_on TIMESTAMP NULL,
  ADD COLUMN "key" VARCHAR(255) NULL UNIQUE;

UPDATE feedback
  SET key = gen_random_uuid();

UPDATE feedback
  SET handled_on = NOW()
  WHERE feedback.handled IS TRUE;

ALTER TABLE feedback
  ALTER COLUMN "key" SET NOT NULL;