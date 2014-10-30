alter table `site_article_group` add index index_f_l (`footerLeft`) ;
alter table `site_article_group` add index index_f_r (`footerRight`) ;
alter table `site_article_group` add index index_f_id (`id`,`footerLeft`,`footerRight`) ;
alter table `site_article_group` add index index_f_2 (`footerLeft`,`footerRight`) ;
alter table `site_article_group` add index index_p_o (`parentGroupID`,`displayOrder`) ;

alter table `site_article_thread` add index index_gid (`articleGroupID`) ;
alter table `site_article_thread` add index index_uid (`userID`) ;
alter table `site_article_thread` add index index_views (`views`) ;
alter table `site_article_thread` add index index_t_a (`topOne`,`addTime`) ;
alter table `site_article_thread` add index index_t_v (`topOne`,`views`) ;
alter table `site_article_thread` add index index_t_id (`topOne`,`id`) ;
alter table `site_article_thread` add index index_gid_s_t_a (`articleGroupID`,`state`,`topOne`,`addTime`) ;