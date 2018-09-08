/*
 Navicat PostgreSQL Data Transfer

 Source Server         : local
 Source Server Type    : PostgreSQL
 Source Server Version : 100005
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100005
 File Encoding         : 65001

 Date: 08/09/2018 14:21:44
*/


-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS "public"."User";
CREATE TABLE "public"."User" (
  "id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL,
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "role" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."User" OWNER TO "root";

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO "public"."User" VALUES ('1', 'dsx', 'dsx', 'user');
INSERT INTO "public"."User" VALUES ('2', 'www', 'www', 'user');
INSERT INTO "public"."User" VALUES ('3', 'ffff', 'ffff', 'user');
INSERT INTO "public"."User" VALUES ('b9b9f1ad-914b-4801-87bf-f254ebfc5acb', 'woca', 'ccc', 'admin');
INSERT INTO "public"."User" VALUES ('25bf9d54-6b49-439f-b5f1-09c7eaa8c418', '今天天气好晴朗', 'qinglanggegui', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "public"."role";
CREATE TABLE "public"."role" (
  "id" varchar(36) COLLATE "pg_catalog"."default",
  "role" varchar(255) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "public"."role" OWNER TO "root";

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO "public"."role" VALUES ('25bf9d54-6b49-439f-b5f1-09c7eaa8c412', 'user');
INSERT INTO "public"."role" VALUES ('25bf9d54-6b49-439f-b5f1-09c7eaa8c518', 'admin');
COMMIT;

-- ----------------------------
-- Primary Key structure for table User
-- ----------------------------
ALTER TABLE "public"."User" ADD CONSTRAINT "User_pkey" PRIMARY KEY ("id");
