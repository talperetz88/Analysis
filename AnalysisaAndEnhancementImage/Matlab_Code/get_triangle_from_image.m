function mat = get_triangle_from_image(path, new_file_name)
%print(cellstr(path))
%Clear Memory & Command Window
clc;
set(0,'RecursionLimit',8000)

nBins=20;
winSize=11;
nClass=12;

%nBins=7;
%winSize=3;
%nClass=7;

%for winSize=1:2:35
%  for nBins=3:1:20    
%   for nClass=2:1:20
   %nBins
   %winSize
   %nClass
%Parameters for the Segmentation


%Read Input Image
inImg = imread(path);
ImInfo = imfinfo(path);

%inImg = imread('lar2.jpg');
%ImInfo = imfinfo('lar2.jpg');
%imshow(inImg);title('Input Image');

%Segmentation
outImg = colImgSeg(inImg, nBins, winSize, nClass);

%Displaying Output
%figure;imshow(outImg);title('Segmentation Maps');
colormap('default');

rgbImage = ind2rgb(outImg, jet(256));

%%%SAVE IMAGE AFTER SEGMENTATION
%imwrite(rgbImage,strcat('C:\Users\user\Desktop\attachments\nBins_',int2str(nBins),'_winSize_',int2str(winSize),'_nClass_',num2str(nClass),'.jpg'));

%figure;
%ImInfo = imfinfo('lar1.jpg');
pixelsX=ImInfo.Width;
pixelsY=ImInfo.Height;
I2 = imcrop(rgbImage,[ round((pixelsX/2)-40) round((pixelsY/2)-30) 100 100]);

rgb_columns = reshape(I2, [], 3);
size(rgb_columns);
[unique_colors, m, n] = unique(rgb_columns, 'rows');
color_counts = accumarray(n, 1);

[max_count, idx] = max(color_counts);
bw = n == idx;
bw = reshape(bw, size(I2, 1), size(I2, 2));

first_clust_cat_x=round((pixelsX/2)-200);
first_clust_cat_y=round((pixelsY/2)-200);

I2 = imcrop(rgbImage,[ first_clust_cat_x first_clust_cat_y 400 418]);

I5=imcrop(inImg,[ first_clust_cat_x first_clust_cat_y 400 400]);

rgb_columns = reshape(I2, [], 3);
size(rgb_columns);
[unique_colors1, m1, n1] = unique(rgb_columns, 'rows');
color_counts = accumarray(n1, 1);

for i=1:1:size(unique_colors1,1)
    
    if unique_colors(idx,:)==unique_colors1(i,:)
        i1=i
        unique_colors(idx,:)
        unique_colors1(i,:)
    end
end
bw = n1 == i1;
bw = reshape(bw, size(I2, 1), size(I2, 2));

%imshow(bw);title('BEFORE IMFILL');

aa=imfill(bw,'holes');
% REMOVE SMALL OBJECTS
mat=bwareaopen(aa, 1500);

%print
%imshow(aa);
%SAVE FINAL IMAGE IMAGE
imwrite(mat, new_file_name); 
%WE CAN USE FULL PATH AND PASS ONLY FILENAME
%folder_path = 'C:\Users\user\Desktop\matlab func';
%imwrite(mat, fullfile(folder_path, new_file_name)); 
mat;
end