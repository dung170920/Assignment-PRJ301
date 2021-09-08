CREATE DATABASE [PRJ301_SE1506_Group 5]
GO
 
USE [PRJ301_SE1506_Group 5]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 7/11/2021 11:00:32 AM ******/

CREATE TABLE [dbo].[Account](
	[userID] [varchar](20) NOT NULL,
	[password] [int] NOT NULL,
	[fullName] [nvarchar](50) NOT NULL,
	[roleID] [int] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Book]    Script Date: 7/11/2021 11:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[bookId] [varchar](10) NOT NULL,
	[description] [nvarchar](250) NOT NULL,
	[price] [float] NULL,
	[bookName] [nvarchar](250) NOT NULL,
	[author] [nvarchar](250) NOT NULL,
	[yearOfProduction] [int] NULL,
	[quantity] [int] NULL,
	[bookImgURL] [varchar](50) NOT NULL,
	[categoryID] [nvarchar](10) NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[bookId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 7/11/2021 11:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [nvarchar](10) NOT NULL,
	[categoryName] [varchar](250) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 7/11/2021 11:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[paymentId] [int] IDENTITY(1,1) NOT NULL,
	[userID] [varchar](20) NOT NULL,
	[dateCreate] [date] NOT NULL,
	[totalPayment] [float] NOT NULL,
	[address] [nvarchar](50) NOT NULL,
	[phoneNumber] [varchar](20) NOT NULL,
	[status] [int] NOT NULL,
 CONSTRAINT [PK_Payment_1] PRIMARY KEY CLUSTERED 
(
	[paymentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PaymentDetail]    Script Date: 7/11/2021 11:00:32 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PaymentDetail](
	[paymentId] [int] NOT NULL,
	[bookId] [varchar](10) NOT NULL,
	[quantity] [int] NOT NULL,
	[subTotal] [float] NOT NULL,
 CONSTRAINT [PK_PaymentDetail] PRIMARY KEY CLUSTERED 
(
	[paymentId] ASC,
	[bookId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([userID], [password], [fullName], [roleID]) VALUES (N'a001', 123456, N'user01', 1)
GO
INSERT [dbo].[Account] ([userID], [password], [fullName], [roleID]) VALUES (N'a002', 123456, N'user01', 0)
GO
INSERT [dbo].[Account] ([userID], [password], [fullName], [roleID]) VALUES (N'dung123', 123456, N'user01', 0)
GO
INSERT [dbo].[Account] ([userID], [password], [fullName], [roleID]) VALUES (N'phuong456', 123456, N'user01', 0)
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B001', N'Đắc nhân tâm (được lòng người), tên tiếng Anh là How to Win Friends and Influence People là một quyển sách nhằm tự giúp bản thân (self-help) bán chạy nhất từ trước đến nay.', 350, N'Đắc Nhân Tâm', N'Dale Carnegie', 1980, 300, N'01.png', N'C003')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B002', N'Great', 216, N'Trên Đường Băng', N'Tony', 2009, 46, N'02.png', N'C003')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B003', N'Nice', 120, N'Tuổi Trẻ Đáng Giá Bao Nhiêu', N'Rosie Nguyễn', 2018, 45, N'03.png', N'C003')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B004', N'Good', 78, N'Mắt Biếc', N'Nguyễn Nhật Ánh', 2011, 50, N'04.png', N'C001')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B005', N'Great', 217, N'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', N'Nguyễn Nhật Ánh', 2015, 60, N'05.png', N'C001')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B006', N'Nice', 147, N'Tôi Là Beto', N'Nguyễn Nhật Ánh', 2018, 80, N'06.png', N'C001')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B007', N'rình bày một cách dễ hiểu các khái niệm nền tảng về nấu nướng cho người mới bắt đầu.
Cung cấp nhiều bí quyết hữu ích cho các bạn trẻ sống tự lập, mong muốn tự tay nấu bữa ăn ngon cho những người thân yêu.', 215, N'Nồi Niêu Xoong Chảo', N'Lang Vi', 2012, 48, N'07.png', N'C002')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B008', N'Great', 390, N'Trái Tim Của Chef', N'Hungazit Nguyễn', 2019, 60, N'08.png', N'C002')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B009', N'Nice', 230, N'100 Món Ăn Ngày Thường', N'Nguyễn Thi Phương', 2015, 60, N'09.png', N'C002')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B010', N'new book', 50, N'nhà giả kim', N'Paulo Coelho', 1988, 100, N'test.png', N'C001')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B011', N'Đánh thức con người phi thường trong bạn” là cuốn sách giúp người đọc khám phá giá trị tiềm ẩn của bản thân để tạo nên những kết quả chính mình không ngờ đến.', 5.059999942779541, N'Đánh Thức Con Người Phi Thường Trong Bạn', N'Anthony Robbins', 2020, 100, N'KNS_danhthuc.jpg', N'C003')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B012', N'Tác phẩm Nữ Sinh của nhà văn Nguyễn Nhật Ánh là cuốn truyện dài dành cho tuổi mới lớn. Câu chuyện kể về những cô gái học trò nghịch ngợm nhưng rất giàu tình cảm.', 60, N'Nữ Sinh', N'Nguyễn Nhật Ánh', 2018, 100, N'TT-nusinh.jpg', N'C001')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B013', N'Cân bằng phương trình của cuộc đời không dễ, cuốn sách này không chỉ là lý thuyết còn cho bạn những kĩ năng, phướng pháp tận tình nhất để giúp bạn làm chủ cảm xúc của mình.', 30, N'Cân Bằng Cảm Xúc, Cả Lúc Bão Giông', N'Richard Nicholls', 2020, 100, N'KNS-canbang.jpg', N'C003')
GO
INSERT [dbo].[Book] ([bookId], [description], [price], [bookName], [author], [yearOfProduction], [quantity], [bookImgURL], [categoryID]) VALUES (N'B014', N'good', 32, N'Nơi Nào Đông Ấm', N'Cố Tây Tước', 2014, 100, N'TT-dongam.jpg', N'C001')
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C001', N'Tieu Thuyet')
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C002', N'Sach Nau an')
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'C003', N'Sach Ky Nang Song')
GO
SET IDENTITY_INSERT [dbo].[Payment] ON 
GO
INSERT [dbo].[Payment] ([paymentId], [userID], [dateCreate], [totalPayment], [address], [phoneNumber], [status]) VALUES (1, N'a002', CAST(N'2021-07-04' AS Date), 360, N'123/45 q12 tphcm', N'0123456789', 1)
GO
INSERT [dbo].[Payment] ([paymentId], [userID], [dateCreate], [totalPayment], [address], [phoneNumber], [status]) VALUES (2, N'a002', CAST(N'2021-07-04' AS Date), 360, N'123/45 q12 tphcm', N'0123456789', 0)
GO
INSERT [dbo].[Payment] ([paymentId], [userID], [dateCreate], [totalPayment], [address], [phoneNumber], [status]) VALUES (3, N'a002', CAST(N'2021-07-04' AS Date), 304, N'4859 q9 tphcm', N'0100000012', 0)
GO
INSERT [dbo].[Payment] ([paymentId], [userID], [dateCreate], [totalPayment], [address], [phoneNumber], [status]) VALUES (4, N'a002', CAST(N'2021-07-04' AS Date), 657, N'q1 tphcm', N'0123456123', 0)
GO
INSERT [dbo].[Payment] ([paymentId], [userID], [dateCreate], [totalPayment], [address], [phoneNumber], [status]) VALUES (5, N'phuong456', CAST(N'2021-07-05' AS Date), 60, N'q1 tphcm', N'0123456123', 0)
GO
INSERT [dbo].[Payment] ([paymentId], [userID], [dateCreate], [totalPayment], [address], [phoneNumber], [status]) VALUES (6, N'phuong456', CAST(N'2021-07-07' AS Date), 433, N'4859 q9 tphcm', N'0123456123', 0)
GO
SET IDENTITY_INSERT [dbo].[Payment] OFF
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (1, N'B001', 1, 350)
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (2, N'B001', 1, 350)
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (3, N'B002', 1, 216)
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (3, N'B004', 1, 78)
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (4, N'B005', 1, 217)
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (4, N'B007', 2, 430)
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (5, N'B010', 1, 50)
GO
INSERT [dbo].[PaymentDetail] ([paymentId], [bookId], [quantity], [subTotal]) VALUES (6, N'B002', 2, 432)
GO
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [FK_Book_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [FK_Book_Category]
GO
ALTER TABLE [dbo].[Payment]  WITH CHECK ADD  CONSTRAINT [FK_Payment_Account] FOREIGN KEY([userID])
REFERENCES [dbo].[Account] ([userID])
GO
ALTER TABLE [dbo].[Payment] CHECK CONSTRAINT [FK_Payment_Account]
GO
ALTER TABLE [dbo].[PaymentDetail]  WITH CHECK ADD  CONSTRAINT [FK_PaymentDetail_Book] FOREIGN KEY([bookId])
REFERENCES [dbo].[Book] ([bookId])
GO
ALTER TABLE [dbo].[PaymentDetail] CHECK CONSTRAINT [FK_PaymentDetail_Book]
GO
ALTER TABLE [dbo].[PaymentDetail]  WITH CHECK ADD  CONSTRAINT [FK_PaymentDetail_Payment] FOREIGN KEY([paymentId])
REFERENCES [dbo].[Payment] ([paymentId])
GO
ALTER TABLE [dbo].[PaymentDetail] CHECK CONSTRAINT [FK_PaymentDetail_Payment]
GO
USE [master]
GO
ALTER DATABASE [PRJ301_SE1506_Group 5] SET  READ_WRITE 
GO
