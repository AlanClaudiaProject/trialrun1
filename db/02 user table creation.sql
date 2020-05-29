USE [trialrun1]
GO

/****** Object:  Table [dbo].[user]    Script Date: 29/5/2020 1:19:19 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user](
	[userId] [bigint] NOT NULL,
	[type] [nvarchar](max) NOT NULL,
	[name] [nvarchar](max) NOT NULL,
	[address] [nvarchar](max) NOT NULL,
	[phoneNumber] [int] NOT NULL,
	[loginId] [nvarchar](50) NOT NULL,
	[password] [nvarchar](max) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO


